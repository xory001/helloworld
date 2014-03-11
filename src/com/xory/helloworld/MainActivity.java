package com.xory.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	final private String TAG = "helloworld.MainActivity";
	private Button mStartedServiceTest;
	private Button mBoundServiceTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i( TAG, "onCreate" );
		mStartedServiceTest = (Button)findViewById( R.id.StartedServiceTest );
		mStartedServiceTest.setOnClickListener( this );
		mBoundServiceTest = (Button)findViewById( R.id.BoundServiceTest );
		mBoundServiceTest.setOnClickListener( this );
		
		findViewById( R.id.btn_draw_activity ).setOnClickListener( this );
		
		findViewById( R.id.btn_bc_send ).setOnClickListener( this );
		findViewById( R.id.btn_send_inner_cast ).setOnClickListener( this );
		findViewById( R.id.btn_send_global_cast ).setOnClickListener( this );
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public void onClick(View v) {
		switch ( v.getId() )
		{
		case R.id.BoundServiceTest:
			Log.i( TAG, "BoundServiceTest clicked" );
			break;
			
		case R.id.StartedServiceTest:
			Log.i( TAG, "StartedServiceTest clicked" ); 
			Intent intent = new Intent( this, StartedServiceActivity.class );
			startActivity( intent );
			break;
			
		case R.id.btn_draw_activity:
			startActivity( new Intent( this, DrawActivity.class ) );
			break;
			
		case R.id.btn_send_global_cast:
			Intent cbIntent = new Intent( BroadcastRecvTest.TEST_ACTION );
			sendBroadcast( cbIntent );
			break;
		case R.id.btn_send_inner_cast:
			sendBroadcast(  new Intent( BroadcastActivity.TEST_ACTION ) );
			break;	
			
		default:
			break;
		}
		
	}
}
