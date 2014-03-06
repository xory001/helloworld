package com.xory.helloworld;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class StrartedIntentSerivce extends IntentService {
	
	final private String TAG = Const.TAG_APP;
	public StrartedIntentSerivce() {
		super("StrartedIntentSerivce");
		Log.i( TAG, "StrartedIntentSerivce::StrartedIntentSerivce" );
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i( TAG, intent.getStringExtra( "name" ) );

	}

	@Override
	public void onDestroy() {
		Log.i( TAG, "onDestroy" );
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i( Const.TAG_APP, "on StrartedIntentSerivce bind" );
		
		return new Binder()
		{
			public void Add()
			{
				Log.i( Const.TAG_APP, "StrartedIntentSerivce add" );
			}
			public void Del()
			{
				Log.i( Const.TAG_APP, "StrartedIntentSerivce del");
			}
		};
		
	}
}
