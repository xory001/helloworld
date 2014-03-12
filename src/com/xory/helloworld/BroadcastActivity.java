package com.xory.helloworld;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastActivity extends Activity {
	
	public static final String TEST_ACTION = "BroadcastActivity_inner_action"; 
	private DynamicBroadcastRecv dynamicBroadcastRecv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_dynamic_bc );
		dynamicBroadcastRecv = new DynamicBroadcastRecv();
		
		IntentFilter intentFilter = new IntentFilter( Intent.ACTION_UMS_CONNECTED );
		intentFilter.addAction( Intent.ACTION_UMS_DISCONNECTED );
		intentFilter.addAction( Intent.ACTION_BATTERY_CHANGED );
		registerReceiver( dynamicBroadcastRecv, intentFilter );
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver( dynamicBroadcastRecv );
	}
	
	@Override
	protected void onStart() {
		super.onStart();
//		IntentFilter intentFilter = new IntentFilter( Intent.ACTION_UMS_CONNECTED );
//		intentFilter.addAction( Intent.ACTION_UMS_DISCONNECTED );
//		intentFilter.addAction( Intent.ACTION_BATTERY_CHANGED );
//		registerReceiver( dynamicBroadcastRecv, intentFilter );
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	//	unregisterReceiver( dynamicBroadcastRecv );
	}
	
	public static class TestBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText( context, "onReceive:" + intent.getAction(), Toast.LENGTH_SHORT ).show();
			
		}
	}
	
	class DynamicBroadcastRecv extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText( context, "DynamicBroadcastRecv::onReceive:" + intent.getAction(), Toast.LENGTH_SHORT ).show();
		}
	}
}
