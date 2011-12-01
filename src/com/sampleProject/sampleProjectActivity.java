package com.sampleProject;

import android.os.Bundle;
import android.view.WindowManager;

import com.phonegap.DroidGap;
import com.urbanairship.UAirship;

public class sampleProjectActivity extends DroidGap {
	private static sampleProjectActivity instance = null;
	private sendJavascript mc;

	public sampleProjectActivity () {
		instance = this;
	}

	public static sampleProjectActivity getInstance() {
		return instance;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        super.init();
        
        // Eger notification bar kalksin istenirse alttaki kodu calistir.
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN); 

        mc = new sendJavascript(this, appView);
        appView.addJavascriptInterface(mc, "MyCls");
        super.setIntegerProperty("splashscreen", R.drawable.splash);
        
        
        super.loadUrl("file:///android_asset/www/index.html", 1000);
        
        super.addService("PushNotificationPlugin", "com.sampleProject.plugins.PushNotificationPlugin");
    }
    
    @Override
    public void onStart() {
        super.onStart();
        UAirship.shared().getAnalytics().activityStarted(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        UAirship.shared().getAnalytics().activityStopped(this);
    }
}