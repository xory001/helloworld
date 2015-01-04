/**
 * 
 */
package com.xory.mutilmedia;

import javax.security.auth.login.LoginException;

import com.xory.helloworld.R;
import com.xory.lib.utility.LogInfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author xory
 * 
 */
public class ActivityCamera extends Activity implements OnClickListener {

	enum OPERATE_TYPE {
		OP_SHOOT_THUMBNAIL(1), OP_SHOOT_THUMBNAIL_CORP(2), OP_SHOOT_BIG(3), OP_SHOOT_BIG_CORP(
				4), OP_SELECT(5), OP_SELECT_CORP(6); // 内部实例初始化,所以必须提供构造函数,因为内部使用,所以构造函数必须为
														// friendly或者private

		private int mnVal;

		OPERATE_TYPE(int nVal) {
			mnVal = nVal;
		}

		int getVal() {
			return mnVal;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mutilmedia_camera);

		// set btn listener
		findViewById(R.id.btn_shoot_thumbnail).setOnClickListener(this);
		findViewById(R.id.btn_shoot_thumbnail_corp).setOnClickListener(this);
		findViewById(R.id.btn_shoot_big_image).setOnClickListener(this);
		findViewById(R.id.btn_shoot_big_corp).setOnClickListener(this);
		findViewById(R.id.btn_select_image).setOnClickListener(this);
		findViewById(R.id.btn_select_corp).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_shoot_thumbnail:

			break;

		case R.id.btn_shoot_thumbnail_corp:
			break;

		case R.id.btn_shoot_big_image:
			break;

		case R.id.btn_shoot_big_corp:
			break;

		case R.id.btn_select_image:
			selectLocalImage();
			break;

		case R.id.btn_select_corp:
			selectLocalImageAndCorp();
			break;

		}

	}

	private void selectLocalImage() {
		try {

			// StringBuilder strSavePic = new StringBuilder( "file://" +
			// strSavePath );
			// Uri uriSave = Uri.parse( strSavePic.toString() );
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*"); // 如果不指明选择类型,则返回的时候,resultCode==0,data==null
			// intent.putExtra( "crop", "true");
			// intent.putExtra( "aspectX", xOutSize );
			// intent.putExtra( "aspectY", yOutSize );
			// intent.putExtra( "outputX", xOutSize );
			// intent.putExtra( "outputY", yOutSize );
			// intent.putExtra( "scale", true );
			// //如果为FALSE,则会忽略outputX|Y的2个参数,根据选定的区域输出实际大小,,为TRUE则缩放到outputX|Y的大小
			// intent.putExtra( "return-data", false );
			// intent.putExtra( MediaStore.EXTRA_OUTPUT, uriSave );
			// intent.putExtra("outputFormat",
			// Bitmap.CompressFormat.JPEG.toString());
			// intent.putExtra("noFaceDetection", true); // no face detection
			startActivityForResult(intent, OPERATE_TYPE.OP_SELECT.getVal());
		}
		catch (Exception e) {

		}
	}

	private void selectLocalImageAndCorp() {
		try {
			// StringBuilder strSavePic = new StringBuilder( "file://" +
			// strSavePath );
			// Uri uriSave = Uri.parse( strSavePic.toString() );
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*"); // 如果不指明选择类型,则返回的时候,resultCode==0,data==null
			// intent.putExtra( "crop", "true");
			// intent.putExtra( "aspectX", xOutSize );
			// intent.putExtra( "aspectY", yOutSize );
			// intent.putExtra( "outputX", xOutSize );
			// intent.putExtra( "outputY", yOutSize );
			// intent.putExtra( "scale", true );
			// //如果为FALSE,则会忽略outputX|Y的2个参数,根据选定的区域输出实际大小,,为TRUE则缩放到outputX|Y的大小
			// intent.putExtra( "return-data", false );
			// intent.putExtra( MediaStore.EXTRA_OUTPUT, uriSave );
			// intent.putExtra("outputFormat",
			// Bitmap.CompressFormat.JPEG.toString());
			// intent.putExtra("noFaceDetection", true); // no face detection
			startActivityForResult(intent, OPERATE_TYPE.OP_SELECT.getVal());
		}
		catch (Exception e) {
			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		LogInfo.i("onActivityResult, resultCode=" + requestCode
				+ ", resultCode=" + resultCode + ", data=" + data);

		if (OPERATE_TYPE.OP_SHOOT_THUMBNAIL.getVal() == requestCode) {

		} else if (OPERATE_TYPE.OP_SHOOT_THUMBNAIL_CORP.getVal() == requestCode) {

		} else if (OPERATE_TYPE.OP_SHOOT_BIG.getVal() == requestCode) {

		} else if (OPERATE_TYPE.OP_SHOOT_BIG_CORP.getVal() == requestCode) {

		} else if (OPERATE_TYPE.OP_SELECT.getVal() == requestCode) {
			if (RESULT_OK == resultCode) {
				if (null != data) {
					Uri uri = data.getData();
					LogInfo.i("onActivityResult, OP_SELECT, data=" + uri);
				} else {
					LogInfo.i("onActivityResult, OP_SELECT, data=null");
				}
			}
		} else if (OPERATE_TYPE.OP_SELECT_CORP.getVal() == requestCode) {

		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
