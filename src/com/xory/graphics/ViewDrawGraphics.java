package com.xory.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

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
		
		canvas.drawLine( 100, 100, nWid - 10 , 100, m_paint );
		canvas.rotate( 10 );
		m_paint.setColor( Color.BLUE );
		canvas.drawLine( 100, 100, nWid - 10 , 100, m_paint );
		
		canvas.rotate( -20 );
		m_paint.setColor( Color.GREEN );
		canvas.drawLine( 100, 100, nWid - 10 , 100, m_paint );
		
		
	}
	

}
