/**
 * 
 */
package com.xory.helloworld;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.xory.utility.StatusBarMgr;

/**
 * @author xory
 * 
 */
public class ActivityNotification extends Activity implements OnClickListener {

	private ProgressBar mProgress;
	private int mlProgress = 0;
	private Timer timer;
	private HandleProgress mHandle = new HandleProgress( this );
	private NotificationManager  mNotifyMgr;
	private int m_nNotifyID = 0;
	private String m_strAction = "recv msg from notify clicked!";

	private BCRecvNotify m_BCRecv = new BCRecvNotify();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		findViewById(R.id.btn_start).setOnClickListener(this);
		findViewById(R.id.btn_stop).setOnClickListener(this);

		mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		mProgress.setMax(100);

		mNotifyMgr = (NotificationManager)getSystemService( NOTIFICATION_SERVICE );
		m_nNotifyID = (int)System.currentTimeMillis();
		
		Log.i( Const.TAG_APP, "main thread:" + Thread.currentThread().getId() );
	}
	

	@Override
	public void onClick(View v) {
		if (R.id.btn_start == v.getId()) {
//			timer = new Timer(); //被cancel之后不能继续调用schedule,否则报错
//			timer.schedule(new taskProgress(), 100, 1000 );
			
			Notification notifyStart = new Notification();
			notifyStart.icon = R.drawable.ic_launcher;
			notifyStart.tickerText = "it is notify start";
			notifyStart.flags = Notification.FLAG_AUTO_CANCEL;
			notifyStart.defaults = Notification.DEFAULT_SOUND;
			Intent acIntent = new Intent( this, ActivityNotification.class );
			PendingIntent pdIntent = PendingIntent.getActivity( this, 0, acIntent, 0 );
			RemoteViews remoteView = new RemoteViews( getPackageName() , R.layout.layout_notify );
			notifyStart.contentIntent = pdIntent;
			notifyStart.contentView = remoteView;
			PendingIntent bcIntent = PendingIntent.getBroadcast( this, 0, new Intent( m_strAction ), 0 );
			remoteView.setOnClickPendingIntent( R.id.button1, bcIntent );
			
			registerReceiver( m_BCRecv, new IntentFilter( m_strAction ) );
			
			mNotifyMgr.notify( m_nNotifyID , notifyStart );
		}
		else if (R.id.btn_stop == v.getId()) {
			if ( null != timer ){
				timer.cancel();
				timer = null;				
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if ( null != timer )
		{
			timer.cancel();
		}
		
		unregisterReceiver( m_BCRecv );
	}
	
	public static void notifyClick( String a ){
		Log.i( Const.TAG_APP , "notify clicked:" + a );
		//Toast.makeText(v.getContext() , "notify clicked", Toast.LENGTH_SHORT ).show();
	}
	
	//如果不定义成静态类,会有内存泄露问题
		static class HandleProgress extends Handler {
			private final WeakReference< ActivityNotification > mActivity;
			
			HandleProgress( ActivityNotification activity ){
				mActivity = new WeakReference<ActivityNotification>( activity );
			}
			@Override
			public void handleMessage( Message msg ) {
				switch( msg.what ){
					case 1:
						if ( null != mActivity.get() ){
							mActivity.get().mProgress.setProgress( mActivity.get().mlProgress++);
						}
						break;
						
					case 2:
						break;
						
					default:
						break;
				}
			}
		}

		class taskProgress extends TimerTask {

			@Override
			public void run() {
			//	progress.setProgress(lProgress++);
				Log.i(Const.TAG_APP, "timer task thread:"
						+ Thread.currentThread().getId());
				Message msg = Message.obtain( mHandle, 1 );
				mHandle.sendMessage( msg );
			}
		}
		
		class BCRecvNotify extends BroadcastReceiver{
			@Override
			public void onReceive(Context context, Intent intent) {
				mNotifyMgr.cancel( m_nNotifyID );
				StatusBarMgr barMgr = new StatusBarMgr( context );
				barMgr.collapse();
                 
				Toast.makeText( context , "click from:" + intent.getAction(), Toast.LENGTH_SHORT ).show();
			}
		}
}
