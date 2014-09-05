/**
 * 
 */
package com.xory.utility;

import java.lang.reflect.Method;

import android.content.Context;
import android.util.Log;

/**
 * @author 朱起伟
 * @date 2014.03.14
 */
/* 
 *   disable statusbar, need permission: 
 *   <uses-permission android:name="android.permission.DISABLE_STATUS_BAR"/> 
	
	Object service = getSystemService ("statusbar");
	try { 
		Class <?> statusBarManager = Class.forName("android.app.StatusBarManager"); 
		Method expand = statusBarManager.getMethod ("disable",int.class); 
		expand.invoke (service,0x00000001); 
	} 
	catch (Exception e) { 
		e.printStackTrace(); 
	}
*/

//<uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />
//<uses-permission android:name="android.permission.STATUS_BAR"/> 
//https://android.googlesource.com/platform/frameworks/base.git/+/android-4.3_r2/core/java/android/app/StatusBarManager.java

//java.lang.IllegalArgumentException: expected receiver of type android.app.StatusBarManager,
//but got java.lang.Class<android.app.StatusBarManager>
public class StatusBarMgr {
	private int m_sysVer42 = 16;
	
	private Context m_context;
	private Object m_serviceStatusBar;
	private  Class< ? > m_sysStatusBarManager; 
	
    public StatusBarMgr( Context context ) {
    	 m_context = context;
    	 if ( null != m_context ){
    		 m_serviceStatusBar = m_context.getSystemService("statusbar"); 
    		 if ( null != m_serviceStatusBar ){
    			 try{
	    			 m_sysStatusBarManager = Class.forName("android.app.StatusBarManager");
	    			 if ( null == m_sysStatusBarManager ){
	        			 Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::StatusBarMgr, can't find calss: \"android.app.StatusBarManager\"");
	 				 }
    			 }
    			 catch( Exception e ){
    				 
    			 }
    		 }
    		 else{
    			 Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::StatusBarMgr, can't find system service : \"statusbar\"");
    		 }
    	 }
    	 else{
    		 Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::StatusBarMgr, contex is null");
    	 }
	}
	
	public void expand() {  
       if ( null != m_sysStatusBarManager ){
    	   try {  
               Method fnExpand = null;  
               if ( IsSystemVersionLessthan42() ) {  
            	   fnExpand = m_sysStatusBarManager.getMethod("expand");  
               } 
               else {  
            	   fnExpand = m_sysStatusBarManager.getMethod("expandNotificationsPanel"); 
               }  
               if ( null != fnExpand ){
            	   fnExpand.setAccessible( true );  
            	   fnExpand.invoke( m_serviceStatusBar ); 
               }
               else{
       			   Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::expand, can't find method: " 
       					   + ( IsSystemVersionLessthan42() ? "expand" : "expandNotificationsPanel" ));
               }
           	}  
            catch (Exception e){
    			Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::expand, execute exception " );
            	e.printStackTrace();
           }  
       }
       else{
			 Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::expand, can't find calss: \"android.app.StatusBarManager\"");
       }
    }  
	
	public void collapse() {
       if ( null != m_sysStatusBarManager ){
    	   try {  
               Method fnExpand = null;  
               if ( IsSystemVersionLessthan42() ) {  
            	   fnExpand = m_sysStatusBarManager.getMethod("collapse");  
               } 
               else {  
            	   fnExpand = m_sysStatusBarManager.getMethod("collapsePanels"); 
               }  
               if ( null != fnExpand ){
            	   fnExpand.setAccessible( true );  
            	   fnExpand.invoke( m_serviceStatusBar ); 
               }
               else{
       			   Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::collapse, can't find method: " 
       					   + ( IsSystemVersionLessthan42() ? "collapse" : "collapsePanels" ));
               }
           	}  
            catch (Exception e){
    			Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::collapse, execute exception " );
            	e.printStackTrace();
           }  
       }
       else{
			 Log.w( ConstUtil.TAG_XORY_UTIL, "StatusBarMgr::collapse, can't find calss: \"android.app.StatusBarManager\"");
       }
    }
	
	private boolean IsSystemVersionLessthan42(){
    	return android.os.Build.VERSION.SDK_INT <= m_sysVer42;
     }
}
