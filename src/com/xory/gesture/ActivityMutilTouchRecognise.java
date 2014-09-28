/**
 * 
 */
package com.xory.gesture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xory.lib.utility.BaseFunction;
import com.xory.lib.utility.LogInfo;

/**
 * @author xory
 * 
 */
public class ActivityMutilTouchRecognise extends Activity {
	private Map<Integer, List<Float>> mMapPts = new HashMap<Integer, List<Float>>();
	private View mView2Draw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		View root = window.getDecorView();
		// root.setTag( 0 );
		// List<View> list = new LinkedList<View>();
		// list.add( root );
		// emunAllChilds( list, 0 );

		Class cls = window.getDecorView().getClass();
		String canonicalName = cls.getCanonicalName(); // 类名
		Class clsclas = cls.getClass(); // 类对象的类对象
		Class cls_parent = cls.getSuperclass(); // 基类
		Class[] classes = cls.getClasses(); // 一堆类
		Class[] classes_d = cls.getDeclaredClasses(); // 一个类;

		mView2Draw = new View(this) {
			@Override
			protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
				canvas.drawColor(0xff000000);
				int nHei = canvas.getHeight();
				int nWid = canvas.getWidth();
				Paint paint = new Paint();
				paint.setColor( 0xff00ff00 );
				
				for(int nKey: mMapPts.keySet() ){
					List<Float> list = mMapPts.get( nKey );
					float[] pts = new float[list.size() ];
					for( int i = 0; i < list.size(); i++ ){
						pts[i] = list.get( i );
					}
					canvas.drawLines( pts, paint);
				}

				
				
			}
		};

		setContentView(mView2Draw);
		// TextView tv = new TextView( this );
		// tv.setText( "aaa" );
		// setContentView( tv );
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int nAction = event.getActionMasked();
		int nIndex = event.getActionIndex();
		LogInfo.i("onTouchEvent, Action: "
				+ BaseFunction.actionToString(nAction) + ", x: "
				+ event.getX(nIndex) + ", y:" + event.getY(nIndex) );

		switch (nAction) {
		case MotionEvent.ACTION_DOWN: {
			mMapPts.clear();
			List<Float> list = new ArrayList<Float>();
			float x = event.getX();
			float y = event.getY();
			list.add(event.getX(nIndex));
			list.add(event.getY(nIndex));
			mMapPts.put(event.getPointerId(nIndex), list);
			mView2Draw.invalidate();
		}
			break;

		case MotionEvent.ACTION_POINTER_DOWN: {
			float x = event.getX();
			float y = event.getY();
			float x1 = event.getX(nIndex);
			float y1 = event.getY(nIndex);
			List<Float> list = new ArrayList<Float>();
			list.add(event.getX(nIndex));
			list.add(event.getY(nIndex));
			mMapPts.put(event.getPointerId(nIndex), list);
			mView2Draw.invalidate();
		}
			break;

		case MotionEvent.ACTION_MOVE: {
			float x = event.getX();
			float y = event.getY();
			float x1 = event.getX(nIndex);
			float y1 = event.getY(nIndex);
			List<Float> list = mMapPts.get(event.getPointerId(nIndex));
			list.add(event.getX(nIndex));
			list.add(event.getY(nIndex));
			mView2Draw.invalidate();
		}
			break;

		case MotionEvent.ACTION_UP: {
			float x = event.getX();
			float y = event.getY();
			float x1 = event.getX(nIndex);
			float y1 = event.getY(nIndex);
			int j = 0;
			j++;
		}
			break;

		case MotionEvent.ACTION_POINTER_UP: {
			float x = event.getX();
			float y = event.getY();
			float x1 = event.getX(nIndex);
			float y1 = event.getY(nIndex);
			int j = 0;
			j++;
		}
			break;

		}

		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	public void emunAllChilds(List<View> list, int nLevel) {
		if (list.size() > 0) {
			View view = list.remove(0);
			LogInfo.i("emunAllChilds level: " + view.getTag() + ", name: "
					+ view.getClass().getCanonicalName());
			if (view instanceof ViewGroup) {
				for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
					View v = ((ViewGroup) view).getChildAt(i);
					v.setTag(nLevel);
					list.add(v);
				}
			}
			emunAllChilds(list, ++nLevel);
		}
	}
}
