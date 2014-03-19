package com.xory.graphics;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xory.helloworld.R;
import com.xory.helloworld.Const;

public class ActivityGraphicsCustomView extends Activity {
	
	Thread m_thread;
	TextView m_tv1;
	boolean m_bExit = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_graphics_customview );
		m_tv1 = (TextView)findViewById( R.id.textview1 );
	    
		((FrameLayout)findViewById( R.id.frame1 )).addView( new ViewDrawGraphics( this ) );

		m_thread = new Thread( "ActivityGraphicCustomView thread 1"){
		
			@Override
			public void run() {
				while ( !m_bExit )
				{
					Log.i( Const.TAG_APP , "ActivityGraphicCustomView thread 1" );
					try {
						sleep( 1000 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Log.i( Const.TAG_APP , "ActivityGraphicCustomView thread 1 exit" );
			}
		};
		m_thread.start();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		m_bExit = true;
		m_thread.interrupt();
//		if ( null != m_thread )
//		{
//			m_thread.stop();
//		}
	}
}
