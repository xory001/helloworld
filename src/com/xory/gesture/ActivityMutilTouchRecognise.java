/**
 * 
 */
package com.xory.gesture;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author xory
 *
 */
public class ActivityMutilTouchRecognise extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View view = new View(this){
			@Override
			protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
				int nHei = canvas.getHeight();
				int nWid = canvas.getWidth();
				
				canvas.drawColor( 0xff0000ff  );
			}
		};
		
		setContentView( view );
//		TextView tv = new TextView( this );
//		tv.setText( "aaa" );
//		setContentView( tv );
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
	
	 @Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

}
