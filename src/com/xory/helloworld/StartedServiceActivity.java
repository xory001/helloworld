package com.xory.helloworld;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class StartedServiceActivity extends Activity 
					implements OnClickListener, ILocaltionInfoCallback, ServiceConnection {


		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.started_servicce );
		findViewById( R.id.startedservice_start ).setOnClickListener( this );
		findViewById( R.id.startedservice_stop ).setOnClickListener( this );
		findViewById( R.id.startedservice_bind ).setOnClickListener( this );
		findViewById( R.id.startedservice_unbind ).setOnClickListener( this );
	}
	
	@Override
	public void onClick(View v) {
		if ( R.id.startedservice_start == v.getId() ){
			Log.i( Const.TAG_APP, "StartedServiceActivity::onClick start" );
			Intent intent = new Intent( this, StartedIntentSerivce.class );
			intent.putExtra( "name", "start service" );
			startService( intent );
		}
		else if ( R.id.startedservice_stop == v.getId() ){
			
		}
		else if ( R.id.startedservice_bind == v.getId() ){
			Log.i( Const.TAG_APP, "StartedServiceActivity::onClick, bind" );
			Intent intentBind = new Intent( this, StartedIntentSerivce.class );
			intentBind.putExtra( "name", "bind service" );
			bindService( intentBind, this, BIND_AUTO_CREATE );
		}
		else if ( R.id.startedservice_unbind == v.getId() ){
			Log.i( Const.TAG_APP, "StartedServiceActivity::onClick, unbind" );
			unbindService( this );
		}
		
	}

	@Override
	public void onPositonChange() {
		Log.i( Const.TAG_APP, "StartedServiceActivity::onPositonChange" ); 		
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.i( Const.TAG_APP, "StartedServiceActivity::onServiceConnected, service name: " + name.getClassName() );
		((StartedIntentSerivce.SrvBinder) service).Add();
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.i( Const.TAG_APP, "StartedServiceActivity::onServiceDisconnected, service name: " + name.getClassName() );

		
	}
}
