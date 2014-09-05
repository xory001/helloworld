package com.xory.graphics;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xory.helloworld.R;

public class ActivityGraphicsSurfaceView extends Activity {
		
	Timer m_timer = new Timer();
	MyTimeTask m_timeTask = new MyTimeTask();
	SurfaceView m_sv;
	SurfaceHolder m_svHolder;
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView( R.layout.activity_graphics_surfaceview );
			
			m_sv = (SurfaceView)findViewById( R.id.surfaceView1 );
			m_svHolder = m_sv.getHolder();
			m_timer.schedule( m_timeTask, 1000, 500 );
			
		}
		
		@Override
		protected void onDestroy() {
			super.onDestroy();
			m_timer.cancel();
		}
		

		private class MyTimeTask extends TimerTask{
			int count = 0;
			Paint paint = new Paint();
			private int left = 50;  
		    private int top = 5;  
		    private int width = 20;
		    int rotate = 0;
		    Random random = new Random(System.currentTimeMillis()); 
		    
		    //for draw3
		    boolean bFirst = true;
		    int nTextL1 = 100;
		    int nTextL2 = 250;
		    int nTextT = 10;
		    int nText = 0;
		    
			@Override
			public void run() {
				
				draw3();
				
			}
			
			void draw3(){
				if ( nTextT > 500 ){
					return;
				}
				 Canvas canvas = null;  
				 paint.setColor( Color.BLUE );
		        try {  
		        	canvas = m_svHolder.lockCanvas();  
		           	if ( bFirst )
		           	{
		           		canvas.drawText( "" + nText, nTextL1, nTextT, paint ); 
		           	}
		           	else{
		           		canvas.drawText( "" + nText, nTextL2, nTextT, paint ); 
		           	}
		           
		            top += 25;  
		        }catch (Exception e) {  
		            // TODO: handle exception  
		        }finally {  
		            if(canvas != null) {  
		            	m_svHolder.unlockCanvasAndPost(canvas);  
		            }  
		        } 
		        bFirst = !bFirst;
			   nText++;    
			   nTextT += 20;
			}
			
			void draw2(){
				         
	            Canvas canvas = null;
	            while (rotate < 500) {
	                //Date curr = new Date();
	                rotate=rotate+1;
	                try{
		                canvas = m_svHolder.lockCanvas();//获取canvas
		    			paint.setColor( Color.rgb(random.nextInt(255), random.nextInt(255), random
		                        .nextInt(255)));
		                paint.setStrokeWidth(2);/*设置paint的外框宽度*/
		                Path mPath = new Path();
		                mPath.moveTo(random.nextInt(450), random.nextInt(600));
		                mPath.lineTo(random.nextInt(300), random.nextInt(400));
		                mPath.lineTo(random.nextInt(200), random.nextInt(300));
		                mPath.close();
		                canvas.drawPath(mPath, paint );
	                }
	                catch (Exception e) {
	                    e.printStackTrace();
	                }finally{
	                	m_svHolder.unlockCanvasAndPost(canvas); //解锁canvas，提交画好的图像
	                }
				
	            }
			}
			
			void draw1(){
				  if(top > 400) //测试程序，画到纵坐标超过400时不再画  
			            return;  
			          
			        switch(count%5) {  
			        case 0:  
			            paint.setColor(Color.BLUE);  
			            break;  
			        case 1:  
			            paint.setColor(Color.WHITE);  
			            break;  
			        case 2:  
			            paint.setColor(Color.YELLOW);  
			            break;  
			        case 3:  
			            paint.setColor(Color.RED);  
			            break;  
			        case 4:  
			            paint.setColor(Color.GREEN);  
			            break;  
			        }  
			          
			        Canvas canvas = null;  
			        try {  
			            Rect rectDirty = new Rect(left, top, left+width, top+width);  
			            Rect rectangle = new Rect(left, top, left+width, top+width);  
			              
			            canvas = m_svHolder.lockCanvas(rectDirty);  
			            canvas.drawRect(rectangle, paint);  
			            top += 25;  
			        }catch (Exception e) {  
			            // TODO: handle exception  
			        }finally {  
			            if(canvas != null) {  
			            	m_svHolder.unlockCanvasAndPost(canvas);  
			            }  
			        }  
			        count++;  
			}
			
		}
}
