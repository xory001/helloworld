package com.xory.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;

public class BaseFunction {
	private final static String TAG = "com.xory.util.baseFun";
	
	 public final static String[] getVolumeList( Context context )
	{
		LogInfo.i( TAG, "BaseFunction::getVolumeList" );
		
		StorageManager storageManager = (StorageManager)context.getSystemService( Context.STORAGE_SERVICE );
		Method getVolumePaths = null;
		try{
			getVolumePaths = storageManager.getClass().getMethod( "getVolumePaths" );
		}
		catch( NoSuchMethodException e )
		{
			LogInfo.i( TAG, "BaseFunction::getVolumeList, exception 1: " + e.toString() );
			e.printStackTrace();
		}

		if ( null != getVolumePaths )
		{
			String[] paths = null;  
	        try {  
	            paths = (String[]) getVolumePaths.invoke( storageManager );  
	        } 
	        catch (IllegalArgumentException e) {  
	        	LogInfo.i( TAG, "BaseFunction::getVolumeList, exception 2: " + e.toString() );
	        	e.printStackTrace();  
	        }
	        catch (IllegalAccessException e) {  
	        	LogInfo.i( TAG, "BaseFunction::getVolumeList, exception 3: " + e.toString() );
	        	e.printStackTrace();  
	        } 
	        catch (InvocationTargetException e) {  
	        	LogInfo.i( TAG, "BaseFunction::getVolumeList, exception 4: " + e.toString() );
	        	e.printStackTrace();  
	        } 
	        if ( null != paths )
	        {
	        	LogInfo.i( TAG, "BaseFunction::getVolumeList, volume count: " + paths.length );
	        }
	        return paths;  
		}
		return null;
	}
	
	public final static String getExternalStoragePath( Context context )
	{
		String strExternalPath = Environment.getExternalStorageDirectory().getPath();
		String strExternalState =  Environment.getExternalStorageState();
		String str1 = context.getExternalCacheDir().getPath();
		String str2 = context.getCacheDir().getPath();
		boolean b = Environment.isExternalStorageRemovable(); 
	
		//google示例代码: 获得cachedir
	final String cachePath =
            Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() ) ||
                    !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                            context.getCacheDir().getPath();
         return cachePath;
	}
	
//	public final static void ShowPopInfo( Context context, String strInfo )
//	{
//		static Toast _sToast = Toast.makeText( context, strInfo, Toast.LENGTH_LONG );
//	}
	
    /*
     * 采用了新的办法获取APK图标，之前的失败是因为android中存在的一个BUG,通过
     * appInfo.publicSourceDir = apkPath;来修正这个问题，详情参见:
     * http://code.google.com/p/android/issues/detail?id=9151
     * 本代码来自: http://www.cnblogs.com/3dant/archive/2012/04/25/2469913.html
     */
    public final static Drawable getApkIcon( Context context, String apkPath ) {
    	LogInfo.i( TAG, "BaseFunction::getApkIcon, apk path: " + apkPath );
    	PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo( apkPath, PackageManager.GET_ACTIVITIES );
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
            	LogInfo.i( TAG, "BaseFunction::getApkIcon, exception: " + e.toString() );
            	e.printStackTrace();
            }
        }
        return null;
    }
    

    /*发送广播更新媒体库(使得系统生成新图片(比如刚拍摄的照片)的缩略图之类...)
     * 需要以下权限
     * <uses-permission android:name="android.permission.RESTART_PACKAGES" />  
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
     * 来自: http://www.eoeandroid.com/thread-164210-1-1.html
     */ 
    public final static void RefreshMedia( Context context )
    {
    	LogInfo.i( TAG, "BaseFunction::RefreshMedia" );

    	IntentFilter intentfilter = new IntentFilter( Intent.ACTION_MEDIA_SCANNER_STARTED);
    	intentfilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
    	intentfilter.addDataScheme("file");
    	context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
            Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath())));
    }
    
    //扫描所有图像,通过系统内置的contentprovider
    //MIME类型大全: 
    public final static List< Map< String, Object >> getAllImages( Context context )
    {
    	List< Map< String, Object >> list = new ArrayList< Map< String, Object >>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  
        // 获取ContentResolver  
        ContentResolver contentResolver = context.getContentResolver();  
        // 查询的字段  
        String[] projection = { MediaStore.Images.Media._ID,  
                MediaStore.Images.Media.DISPLAY_NAME,  
                MediaStore.Images.Media.DATA, 
                MediaStore.Images.Media.SIZE };  
        // 条件  
        String selection = MediaStore.Images.Media.MIME_TYPE + "=?";  
        // 条件值(這裡的参数不是图片的格式，而是标准，所有不要改动)  
        String[] selectionArgs = { "image/jpeg" };
        // 排序  
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " desc";  
        // 查询sd卡上的图片  
        Cursor cursor = contentResolver.query(uri, projection, selection,  
                selectionArgs, sortOrder); 
        if ( null != cursor ){
        	if ( cursor.moveToFirst() ){
		        do{
		        	Map< String, Object > map = new HashMap< String, Object >();
		       	    map.put( "id", cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media._ID ) ) );
			       	map.put( "name", cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DISPLAY_NAME ) ) );
			       	map.put( "path", cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) ) );
			       	map.put( "size", cursor.getLong( cursor.getColumnIndex( MediaStore.Images.Media.SIZE ) ) / 1024 + "kb");
			       	list.add( map );
		        }while( cursor.moveToNext() );
        	}
        	cursor.close(); //显式关闭
        }

//      Cursor cursor = contentResolver.query( uri, null, null, null, null ); 
//      cursor.moveToFirst();
//      String[] allColumnNames = cursor.getColumnNames();
//      for( int i = 0; i < cursor.getColumnCount(); i++ ){
//    	  map.put( cursor.getColumnName( i ), 
//    			  cursor.getString( i ) );
//      }
        
        return list;
    }

}
