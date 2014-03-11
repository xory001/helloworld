package com.xory.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastRecvTest extends BroadcastReceiver {
	public final static String TEST_ACTION = "BroadcastRecvTest_action"; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText( context, "onReceive:" + intent.getAction(), Toast.LENGTH_SHORT ).show();


	}

}
