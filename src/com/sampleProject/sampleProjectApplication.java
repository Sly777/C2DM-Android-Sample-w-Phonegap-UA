package com.sampleProject;

import org.json.JSONArray;

import com.sampleProject.ua.IntentReceiver;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

public class sampleProjectApplication extends Application {

	final static String TAG = sampleProjectApplication.class.getSimpleName();	
	private static sampleProjectApplication instance;
	public static Plugin gwebView;
	public static String lPushId;

    public sampleProjectApplication () {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
	public void onCreate() {
    	super.onCreate();
    	
		UAirship.takeOff(this);
		PushManager.enablePush();
		PushManager.shared().setIntentReceiver(IntentReceiver.class);
		
		PushPreferences prefs = PushManager.shared().getPreferences();
        prefs.setSoundEnabled(true);
        prefs.setVibrateEnabled(true);

        // apid is a unique identifier for a particular device
        String pushId = prefs.getPushId();
        lPushId = pushId;
        Log.i(TAG, "My Application onCreate - App APID: " + pushId);
        String js = String.format("window.plugins.pushNotification.getApid('%s');", lPushId);
	}
	
    public void onStop() {
		UAirship.land();
	}
}