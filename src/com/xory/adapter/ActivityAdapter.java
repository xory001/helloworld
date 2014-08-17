package com.xory.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.xory.helloworld.R;

public class ActivityAdapter extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_adapter );
		findViewById( R.id.btn_arrayadapter ).setOnClickListener( this );
		findViewById( R.id.btn_simpleAdapter ).setOnClickListener( this );
	}
	
	@Override
	public void onClick(View v) {
		
		switch( v.getId() )
		{
			case R.id.btn_arrayadapter:
			{
				startActivity( new Intent( this, ActivityArrayAdapter.class ) );
			}
			break;
			
			case R.id.btn_simpleAdapter:
			{
				startActivity( new Intent( this, ActivitySimpleAdapter.class ) );
			}
			break;
		}
		
	}
}
