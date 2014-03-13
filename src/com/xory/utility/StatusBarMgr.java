/**
 * 
 */
package com.xory.utility;

/**
 * @author ÷Ï∆Œ∞
 *
 */
//<uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />
//    <uses-permission android:name="android.permission.STATUS_BAR"/> 
//<uses-permission android:name="android.permission.DISABLE_STATUS_BAR"/> 
//https://android.googlesource.com/platform/frameworks/base.git/+/android-4.3_r2/core/java/android/app/StatusBarManager.java

public class StatusBarMgr {

	
	public void OpenNotify() {  
        // TODO Auto-generated method stub  
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;  
        try {  
            Object service = getSystemService("statusbar");  
            Class<?> statusbarManager = Class  
                    .forName("android.app.StatusBarManager");  
            Method expand = null;  
            if (service != null) {  
                if (currentApiVersion <= 16) {  
                    expand = statusbarManager.getMethod("expand");  
                } else {  
                    expand = statusbarManager  
                            .getMethod("expandNotificationsPanel");  
                }  
                expand.setAccessible(true);  
                expand.invoke(service);  
            }  
  
        } catch (Exception e) {  
        }  
  
    }  
	
	private void collapseStatusBar() {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        try {
            Object service = getSystemService("statusbar");
            Class<?> statusbarManager = Class
                    .forName("android.app.StatusBarManager");
            Method collapse = null;
            if (service != null) {
                if (currentApiVersion <= 16) {
                    collapse = statusbarManager.getMethod("collapse");
                } else {
                    collapse = statusbarManager.getMethod("collapsePanels");
                }
                collapse.setAccessible(true);
                collapse.invoke(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	Object service = getSystemService ("statusbar");
    try { 
Class <?> statusBarManager = Class.forName 
("android.app.StatusBarManager"); 
Method expand = statusBarManager.getMethod ("disable",int.class); 
expand.invoke (service,0x00000001); 
} catch (Exception e) { 
e.printStackTrace(); 
}
    
    /**
     * Collapse status panel
     * 
     * @param context
     *            the context used to fetch status bar manager
     */
     public static void collapseStatusBar(Context context) {
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;

            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
	
}
