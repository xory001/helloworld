/**
 * 
 */
package com.xory.views;


import com.xory.helloworld.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author xory
 * @category activity模拟dialog,对话框
 * 
 * @see 参考:http://www.cnblogs.com/winxiang/archive/2012/11/20/2778729.html
 *
 */
public class ActivityDialogStyle extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(  R.layout.activity_views_dlgstyle );
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
