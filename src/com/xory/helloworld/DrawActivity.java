package com.xory.helloworld;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DrawActivity extends Activity implements OnClickListener {
	
	private ImageView ivDraw;
	private Paint paint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.draw_activity );
		findViewById( R.id.drawactivity_start ).setOnClickListener( this );
		findViewById( R.id.drawactivity_clear ).setOnClickListener( this );
		ivDraw = (ImageView)findViewById( R.id.iv_draw_activity );
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.RED);
	}

	@Override
	public void onClick(View v) {
		if ( R.id.drawactivity_start == v.getId() )	{
			Bitmap   baseBitmap = Bitmap.createBitmap( ivDraw.getWidth(),
					ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas  canvas = new Canvas(baseBitmap);
			canvas.drawColor(Color.RED);
			ivDraw.setImageBitmap( baseBitmap );
		}
		
	}
}
