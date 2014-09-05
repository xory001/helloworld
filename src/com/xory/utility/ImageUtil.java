/**
 * 
 */
package com.gensee.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @author xory
 *
 */
public class ImageUtil {
	
	/**
	 * 
	 * @param activity
	 * @param requstCode
	 * @param strSavePath
	 * @param xOutSize
	 * @param yOutSize
	 * @param strSourcePath
	 * @return boolean, true suscced, false failed
	 */
	public static final boolean CropImage( Activity activity, int requstCode, String strSavePath, int xOutSize, int yOutSize, String strSourcePath  ){
		boolean ret = false;
		try{
			StringBuilder strSourcePic = new StringBuilder( "file://" + strSavePath );
			Uri uriSource = Uri.parse( strSourcePic.toString() );
			StringBuilder strSavePic = new StringBuilder( "file://" + strSavePath );
			Uri uriSave = Uri.parse( strSavePic.toString() );
			Intent intent = new Intent( "com.android.camera.action.CROP" );  
			intent.setDataAndType( uriSource, "image/*" ); 
	        intent.putExtra( "crop", "true" );
	        intent.putExtra( "aspectX", xOutSize );
	        intent.putExtra( "aspectY", yOutSize );
	        intent.putExtra( "outputX", xOutSize );
	        intent.putExtra( "outputY", yOutSize );
	        intent.putExtra( "scale", true ); //如果为FALSE,则会忽略outputX|Y的2个参数,根据选定的区域输出实际大小,,为TRUE则缩放到outputX|Y的大小
	        intent.putExtra( "return-data", false );
	        intent.putExtra( MediaStore.EXTRA_OUTPUT, uriSave );
        // intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // intent.putExtra("noFaceDetection", true); // no face detection
	        activity.startActivityForResult( intent, requstCode );
	        ret = true;
		}catch( Exception e ){
			GenseeLog.i( "ImageUtil::CropImage, exception: " + e.toString() );
		}
		return ret;
	}
	
	/**
	 * @param activity
	 * @param requstCode
	 * @param strSavePath
	 * @param xOutSize
	 * @param yOutSize
	 * @return
	 */
	public static final boolean SelectAndCropImage( Activity activity, int requstCode, String strSavePath, int xOutSize, int yOutSize  ){
		boolean ret = false;
		try{
			
			StringBuilder strSavePic = new StringBuilder( "file://" + strSavePath );
			Uri uriSave = Uri.parse( strSavePic.toString() );			
			Intent intent = new Intent( Intent.ACTION_GET_CONTENT );  
            intent.setType( "image/*" );  
            intent.putExtra( "crop", "true");
            intent.putExtra( "aspectX", xOutSize );
            intent.putExtra( "aspectY", yOutSize );
            intent.putExtra( "outputX", xOutSize );
            intent.putExtra( "outputY", yOutSize );
            intent.putExtra( "scale", true ); //如果为FALSE,则会忽略outputX|Y的2个参数,根据选定的区域输出实际大小,,为TRUE则缩放到outputX|Y的大小
            intent.putExtra( "return-data", false );
            intent.putExtra( MediaStore.EXTRA_OUTPUT, uriSave );
         // intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //  intent.putExtra("noFaceDetection", true); // no face detection
	        activity.startActivityForResult( intent, requstCode );
	        ret = true;
		}catch( Exception e ){
			GenseeLog.i( "ImageUtil::SelectAndCropImage, exception: " + e.toString() );
		}
		return ret;
	}
}
