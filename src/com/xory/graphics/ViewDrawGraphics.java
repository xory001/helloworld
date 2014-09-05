package com.xory.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

/*
*canvas的rotate方法,是画布旋转,同时坐标也跟随画布旋转,但是显示区域不变,
*因此如果旋转后的坐标不在屏幕内,则不会显示
*translate为设置坐标原点
*/
public class ViewDrawGraphics extends View {

	private Paint m_paint;
	public ViewDrawGraphics(Context context) {
		super(context);
		
		m_paint = new Paint();
	
		m_paint.setColor( Color.RED );

		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int nDensity = canvas.getDensity();
		int nWid = canvas.getWidth();
		int nHei = canvas.getHeight();
		
		m_paint.setStrokeWidth( 10 );
		canvas.drawLine( 10, 10, nWid - 10 , 10, m_paint );
		m_paint.setStyle( Style.STROKE );
		canvas.drawLine( 10, 40, nWid - 10 , 40, m_paint );
		m_paint.setStrokeWidth( 5 );
		m_paint.setStyle( Style.FILL );
		canvas.drawRect(  10, nHei - 20, nWid - 10 , nHei, m_paint );
		
		m_paint.setStrokeWidth( 2 );
		canvas.drawLine( 200, 200, 300 , 200, m_paint );
		canvas.drawPoint( nWid / 2, nHei / 2, m_paint );
		canvas.rotate( 45, nWid / 2, nHei / 2 );
		canvas.translate( nWid / 2, nHei / 2 );
		m_paint.setColor( Color.BLUE );
//		canvas.drawLine( 100, 200, 200 , 200, m_paint );
//		m_paint.setStrokeWidth( 10 );
//		canvas.drawPoint( 100, 200, m_paint );
//		canvas.drawPoint( 200, 200, m_paint );
		canvas.drawLine( 1, 1, 200 , 200, m_paint );
		
//		canvas.rotate( -45 );
//		m_paint.setColor( Color.GREEN );
//		canvas.drawLine( 100, 200, 200 , 200, m_paint );
		
		
	}
	

}
