package com.xory.graphics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xory.helloworld.R;

public class ActivityGraphicCustomView extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_graphics_view );
		
	    
		((FrameLayout)findViewById( R.id.frame1 )).addView( new ViewDrawGraphics( this ) );

		
	}
}
