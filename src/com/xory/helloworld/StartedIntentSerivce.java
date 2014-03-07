package com.xory.helloworld;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

interface ILocaltionInfoCallback{
	public abstract void onPositonChange();
}

public class StartedIntentSerivce extends IntentService {
	
	final private String TAG = Const.TAG_APP;
	ILocaltionInfoCallback mLocaltionCallBack;
	SrvBinder mBinder;
	
	class SrvBinder extends Binder
	{
		public void Add()
		{
			Log.i( Const.TAG_APP, "StartedIntentSerivce add" );
		}
		public void Del()
		{
			Log.i( Const.TAG_APP, "StartedIntentSerivce del");
		}
	};
	
	public StartedIntentSerivce() {
		super("StartedIntentSerivce");
		//Log.i( TAG, "StartedIntentSerivce::StartedIntentSerivce" );
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i( TAG, "StartedIntentSerivce::onHandleIntent,name= " + intent.getStringExtra( "name" ) );

	}

	@Override
	public void onDestroy() {
		Log.i( TAG, "StartedIntentSerivce::onDestroy" );
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i( Const.TAG_APP, "StartedIntentSerivce::onBind,name= " + intent.getStringExtra( "name" ) );
		mLocaltionCallBack = (ILocaltionInfoCallback)intent.getExtras().get( "LocaltionCallBack" );
		if ( null != mLocaltionCallBack ){
			mLocaltionCallBack.onPositonChange();
		}
		
		if ( null == mBinder ){
			mBinder = new SrvBinder();
			Log.i( Const.TAG_APP, "StartedIntentSerivce::onBind, new SrvBinder= " );
		}
		
		return mBinder;
	}
}
