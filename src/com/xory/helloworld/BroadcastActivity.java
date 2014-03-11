package com.xory.helloworld;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastActivity extends Activity {
	
	public static final String TEST_ACTION = "BroadcastActivity_inner_action"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public static class TestBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText( context, "onReceive:" + intent.getAction(), Toast.LENGTH_SHORT ).show();
			
		}
		
	}
	

}
