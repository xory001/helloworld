package com.xory.app;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xory.helloworld.R;

public class ActivityXMLParser extends Activity implements OnClickListener {
	private String TAG = "ActivityXMLParser";
	private TextView mTVInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_app_xmlparser );
		
		((Button)findViewById( R.id.btn_sax  )).setOnClickListener( this );
		((Button)findViewById( R.id.btn_xmlpull  )).setOnClickListener( this );
		((Button)findViewById( R.id.btn_dom  )).setOnClickListener( this );
		mTVInfo = (TextView)findViewById( R.id.tv_info );
	}

	@Override
	public void onClick(View v) {
		switch( v.getId() ){
		case R.id.btn_sax:
			doDomXmlParser();		
			break;
			
		case R.id.btn_xmlpull:
			doXmlPullParser( R.xml.user_login );
			doXmlPullParser( R.xml.layout );
			break;
			
		case R.id.btn_dom:
			doSAXParser();
			break;
		}
	}

	private void doDomXmlParser() {
		

		
	}
	
	private void doXmlPullParser( int resId ) {
		try{
			XmlResourceParser xmlParser = getResources().getXml( resId );
			int nEventType = xmlParser.next();  //否则XmlResourceParser.START_DOCUMENT会来两次
			while ( nEventType != XmlResourceParser.END_DOCUMENT  ){
				switch( xmlParser.getEventType() ){
				case XmlResourceParser.START_DOCUMENT: //文档开始
					Log.i( TAG, "start document: " );
					break;
					
				case XmlResourceParser.START_TAG: //开始一个标志,可以读取属性
					String strTag = xmlParser.getName();
					String strNameSpace = xmlParser.getNamespace();
					Log.i( TAG, "start tag: " + strTag + ", name space: " + strNameSpace );
					int nAttrib = xmlParser.getAttributeCount();
					for ( int i = 0; i < nAttrib; i++ ){
						//String strNS = xmlParser.getAttributeNamespace( 0 );
						String strAttribName = xmlParser.getAttributeName( i );
						String strAttribValue = xmlParser.getAttributeValue( i );
						Log.i( TAG, "attrib name: " + strAttribName + ", attrib value: " + strAttribValue  );
					}
					break;
					
				case XmlPullParser.TEXT: //读取到标志内容
					String text = xmlParser.getText();
					Log.i( TAG, "text: " + text );
					break;
					
				case XmlResourceParser.END_TAG: //标志结束
					Log.i( TAG, "end tag:  " + xmlParser.getName()  );
					break;
					
				}
				nEventType = xmlParser.next();
			}
		}catch( NotFoundException e  ){
			e.printStackTrace();
		} catch ( XmlPullParserException e ) {
			e.printStackTrace();
		}catch( IOException e ){
			e.printStackTrace();
		}
	}
	
	private void doSAXParser() {
		
	}
}
