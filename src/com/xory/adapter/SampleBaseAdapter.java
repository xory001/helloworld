package com.xory.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xory.helloworld.Const;
import com.xory.helloworld.R;
import com.xory.lib.utility.BaseFunction;

public class SampleBaseAdapter extends BaseAdapter {
	private OnClickListener mClickListener = null;
	private Context mContext = null;
	private LayoutInflater mInflater;
	private List< Map< String, Object >> mListImage = new ArrayList< Map< String, Object >>();

	
	public SampleBaseAdapter() {
	}
	
	public SampleBaseAdapter( Context context, OnClickListener listener )
	{
		mContext = context;
		mClickListener = listener;
		mInflater = LayoutInflater.from( mContext );
		mListImage = BaseFunction.getAllImages( mContext );
	}

	@Override
	public int getCount() {
		//return mListImage.size();
		return 30;
	}

	@Override
	public Object getItem(int position) {
		return mListImage.get( position  );
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//final Holder holder;
		if ( null == convertView ){
			convertView = mInflater.inflate( R.layout.layout_adapter_base_item, null );
//			holder = new Holder();
//			holder.text = ((TextView)convertView.findViewById( R.id.tv_name ));
//			holder.btn = (Button)convertView.findViewById( R.id.btn );
//			convertView.setTag( holder );
		}
		else{
			//holder = (Holder)convertView.getTag();

//			ViewGroup viewParent = (ViewGroup)convertView;
//			for ( int i = 0; i < viewParent.getChildCount(); i++ ){
//				View childView = viewParent.getChildAt( i );
//				Log.i( Const.TAG_APP, "SampleBaseAdapter::getView, pos, chind view id: " + position + ", " + childView.getId() );
//			}
		}
		

		
//		((ImageView)convertView.findViewById( R.id.iv )).
//					setImageURI( Uri.parse( mListImage.get( position ).get( "path").toString() ) );
		((ImageView)convertView.findViewById( R.id.iv )).
			setImageResource( R.drawable.btn_radio_on_selected );
		
		TextView tv = ((TextView)convertView.findViewById( R.id.tv_name ));
		tv.setText( "textview,pos: " + position ); //+ "\nimage szie:" + mListImage.get( position ).get( "size").toString() );
		if ( null != mClickListener ){
			Button btn = (Button)convertView.findViewById( R.id.btn );
			btn.setOnClickListener( mClickListener );
			btn.setText( "btn,pos: " + position );
			btn.setTag( Integer.valueOf( position ) );
		}

		
//		if( null != holder){
//			holder.text.setText( "itme pos: " + position );  
//			holder.btn.setOnClickListener(new OnClickListener() {  
//		        @Override  
//		        public void onClick(View v) {  
//		        	holder.text.setText( "btn click");  
//		        }  
//		    }); 
//		}
		
		return convertView;
	}
	

	//holder的作用就是当item view重新利用的时候,省去findViewById找子id消耗的时间
	class Holder{  
	    TextView text;  
	    Button btn;  
	}  

}
