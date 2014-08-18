package com.xory.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.storage.StorageManager;

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
    public static Drawable getApkIcon( Context context, String apkPath ) {
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

}
