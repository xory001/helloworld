package com.xory.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

public class BaseFunction {
	
	
	 public final static String[] getVolumeList( Context context )
	{
		StorageManager storageManager = (StorageManager)context.getSystemService( Context.STORAGE_SERVICE );
		Method getVolumePaths = null;
		try{
			getVolumePaths = storageManager.getClass().getMethod( "getVolumePaths" );
		}
		catch( NoSuchMethodException e )
		{
			e.printStackTrace();
		}

		if ( null != getVolumePaths )
		{
			String[] paths = null;  
	        try {  
	            paths = (String[]) getVolumePaths.invoke( storageManager );  
	        } catch (IllegalArgumentException e) {  
	            e.printStackTrace();  
	        } catch (IllegalAccessException e) {  
	            e.printStackTrace();  
	        } catch (InvocationTargetException e) {  
	            e.printStackTrace();  
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

}
