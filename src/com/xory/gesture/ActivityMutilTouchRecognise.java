/**
 * 
 */
package com.xory.gesture;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xory.lib.utility.LogInfo;

/**
 * @author xory
 *
 */
public class ActivityMutilTouchRecognise extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		List<View> list = new LinkedList<View>();
		list.add( window.getDecorView()  );
		emunAllChilds( list );
	
		Class cls =  window.getDecorView().getClass();
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
	 
	 public void emunAllChilds( List<View> list ){
		 if( list.size() > 0  ){
			 View view =  list.remove( 0 );
			 LogInfo.i( "emunAllChilds: ", view.getClass().getCanonicalName() );
			 if( view instanceof ViewGroup ){
				 for(  int i = 0; i < ((ViewGroup)view).getChildCount(); i++){
					 list.add( ((ViewGroup)view).getChildAt( i)  );
				 }
			 }
			 emunAllChilds( list );
		 }
	 }
}
