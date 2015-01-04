/**
 * 
 */
package com.xory.views;


import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.xory.helloworld.R;
import com.xory.lib.utility.LogInfo;

/**
 * @author xory
 *
 */
public class ActivityGalleryOfViewFlipper extends Activity {
	private LinearLayout mLinearLayout;
	private ViewFlipper mViewFlipper;
	private Cursor mCursorImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if( null != savedInstanceState ){
			LogInfo.i( "ActivityGalleryOfViewFlipper::onCreate, savedInstanceState != null");
		}
		setContentView( R.layout.activity_views_gallery );
		mViewFlipper = new ViewFlipper( this );
		mViewFlipper.setBackgroundColor(0xff0000ff);
		//mViewFlipper.
		
		int nCount = 0;
		mCursorImage = Media.query( getContentResolver(), Media.EXTERNAL_CONTENT_URI, new String[]{Media.DATA, Media._ID });
		if( (null != mCursorImage) && mCursorImage.moveToFirst()  ){
			do{
				//Options op = new Options()
				long imageId = mCursorImage.getLong( 1 );
				Bitmap tn = Thumbnails.getThumbnail( getContentResolver(), imageId, Thumbnails.MICRO_KIND, null);
				//Bitmap bmp = BitmapFactory.decodeFile( mCursorImage.getString(0) );
				ImageView iv = new ImageView( this );
				iv.setImageBitmap( tn );
				mViewFlipper.addView( iv );
				
				nCount++;
				if( nCount > 3 ){
					break;
				}
				
			}while( mCursorImage.moveToNext() );
		}
		
		
		((FrameLayout)findViewById( R.id.frame )).addView( mViewFlipper );
		mLinearLayout = (LinearLayout)findViewById( R.id.linear );
	}

}
