/**
 * 
 */
package com.xory.helloworld;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

/**
 * @author xory
 * 
 */
public class ActivityNotification extends Activity implements OnClickListener {

	private ProgressBar mProgress;
	private int mlProgress = 0;
	private Timer timer;
	private HandleProgress mHandle = new HandleProgress( this );
	

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		findViewById(R.id.btn_start).setOnClickListener(this);
		findViewById(R.id.btn_stop).setOnClickListener(this);

		mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		mProgress.setMax(100);

		Log.i( Const.TAG_APP, "main thread:" + Thread.currentThread().getId() );

	}

	@Override
	public void onClick(View v) {
		if (R.id.btn_start == v.getId()) {
			timer = new Timer(); //被cancel之后不能继续调用schedule,否则报错
			timer.schedule(new taskProgress(), 100, 1000 );
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
	}
}
