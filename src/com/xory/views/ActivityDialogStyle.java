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
