package com.xory.adapter;

import com.xory.helloworld.R;

import android.app.Activity;
import android.os.Bundle;

public class ActivityBaseAdapter extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_adapter_base );
	}

}
