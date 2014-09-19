/**
 * 
 */
package com.xory.gesture;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

/**
 * @author xory
 *
 */
public class ActivityMutilTouchRecognise extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		View decorView = window.getDecorView();
		Class cls = decorView.getClass();
		String canonicalName =  cls.getCanonicalName(); //类名
		Class clsclas = cls.getClass(); //类对象的类对象
		Class cls_parent = cls.getSuperclass(); //基类
		Class []classes = cls.getClasses(); //一堆类
		Class []classes_d = cls.getDeclaredClasses(); //一个类;
		
		
		
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
