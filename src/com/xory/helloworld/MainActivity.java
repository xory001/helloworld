package com.xory.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.xory.graphics.ActivityGraphics;
import com.xory.utility.BaseFunction;

public class MainActivity extends Activity implements OnClickListener{

	final private String TAG = "helloworld.MainActivity";
	private NotificationManager  notifyMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] volumePaths = BaseFunction.getVolumeList( this );
		
		//String strPath = BaseFunction.getExternalStoragePath( this );
		
		Log.i( TAG, "onCreate" );
		findViewById( R.id.StartedServiceTest ).setOnClickListener( this );
		findViewById( R.id.BoundServiceTest ).setOnClickListener( this );
		
		findViewById( R.id.btn_draw_activity ).setOnClickListener( this );
		
		findViewById( R.id.btn_bc_activity ).setOnClickListener( this );
		findViewById( R.id.btn_send_inner_cast ).setOnClickListener( this );
		findViewById( R.id.btn_send_global_cast ).setOnClickListener( this );
		
		findViewById( R.id.btn_notify_show ).setOnClickListener( this );
		findViewById( R.id.btn_notify_start ).setOnClickListener( this );
		
		findViewById( R.id.btn_start_notify_activity ).setOnClickListener( this );
		
		findViewById( R.id.btn_graphics ).setOnClickListener( this );
		
		notifyMgr = (NotificationManager)getSystemService( NOTIFICATION_SERVICE );
		
		
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
			startActivity( new Intent( this, StartedServiceActivity.class ) );
			break;
			
		case R.id.btn_draw_activity:
			startActivity( new Intent( this, DrawActivity.class ) );
			break;
			
		case R.id.btn_send_global_cast:
			sendBroadcast( new Intent( BroadcastRecvTest.TEST_ACTION ) );
			break;
			
		case R.id.btn_send_inner_cast:
			sendBroadcast(  new Intent( BroadcastActivity.TEST_ACTION ) );
			break;	
			
		case R.id.btn_start_notify_activity:
			startActivity(  new Intent( this, ActivityNotification.class ) );
			break;	
			
		case R.id.btn_bc_activity:
			startActivity( new Intent( this, BroadcastActivity.class ) );
			break;	
			
		case R.id.btn_notify_show:
			Notification notifyShow = new Notification();
			notifyShow.icon = R.drawable.ic_launcher;
			notifyShow.tickerText = "it is notify show";
			notifyShow.defaults = Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE;
			notifyShow.flags = Notification.FLAG_AUTO_CANCEL;
			PendingIntent nullPendIntent = PendingIntent.getActivity( this, 0, new Intent(), 0 );
			notifyShow.setLatestEventInfo( this, "notifyTitle", "notifyText", nullPendIntent );
			notifyMgr.notify( 0x100, notifyShow );
			break;	
			
		case R.id.btn_notify_start:
			Notification notifyStart = new Notification();
			notifyStart.icon = R.drawable.ic_launcher;
			notifyStart.tickerText = "it is notify start";
			notifyStart.flags = Notification.FLAG_AUTO_CANCEL;
			notifyStart.defaults = Notification.DEFAULT_SOUND;
			Intent acIntent = new Intent( this, ActivityNotification.class );
			PendingIntent pdIntent = PendingIntent.getActivity( this, 0, acIntent, 0 );
			notifyStart.setLatestEventInfo( this, "notifyTitle", "notifyText", pdIntent );
			notifyMgr.notify( 0x111 , notifyStart );
			break;	
			
		case R.id.btn_graphics:
			startActivity( new Intent( this, ActivityGraphics.class ) );
			break;	
			
		default:
			break;
		}
		
	}
}
