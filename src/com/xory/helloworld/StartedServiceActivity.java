package com.xory.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class StartedServiceActivity extends Activity implements OnClickListener {


		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.started_servicce );
		findViewById( R.id.startedservice_start ).setOnClickListener( this );
		findViewById( R.id.startedservice_stop ).setOnClickListener( this );
	}
	
	@Override
	public void onClick(View v) {
		if ( R.id.startedservice_start == v.getId() ){
			Intent intent = new Intent( this, StrartedIntentSerivce.class );
			intent.putExtra( "name", "start service" );
			startService( intent );
		}
		else if ( R.id.startedservice_stop == v.getId() ){
			
		}
		
	}
}
