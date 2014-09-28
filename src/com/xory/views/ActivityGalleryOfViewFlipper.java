/**
 * 
 */
package com.xory.views;


import com.xory.helloworld.R;
import com.xory.lib.utility.LogInfo;

import android.app.Activity;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * @author xory
 *
 */
public class ActivityGalleryOfViewFlipper extends Activity {
	private LinearLayout mLinearLayout;
	private ViewFlipper mViewFlipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if( null != savedInstanceState ){
			LogInfo.i( "ActivityGalleryOfViewFlipper::onCreate, savedInstanceState != null");
		}
		setContentView( R.layout.activity_views_gallery );
		mViewFlipper = new ViewFlipper( this );
		mViewFlipper.setBackgroundColor(0xff0000ff);
		((FrameLayout)findViewById( R.id.frame )).addView( mViewFlipper );
		mLinearLayout = (LinearLayout)findViewById( R.id.linear );
	}

}
