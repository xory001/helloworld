package com.xory.graphics;

import com.xory.helloworld.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityGraphics extends Activity implements OnClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_graphics );
		findViewById( R.id.btn_view ).setOnClickListener( this );
		findViewById( R.id.btn_surfaceview ).setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btn_view:
			startActivity( new Intent( this, ActivityGraphicsCustomView.class ) );
			break;
			
		case R.id.btn_surfaceview:
			startActivity( new Intent( this, ActivityGraphicsSurfaceView.class ) );
			break;

		default:
			break;
		}
	}
}
