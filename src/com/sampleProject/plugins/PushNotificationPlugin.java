package com.sampleProject.plugins;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sampleProject.ua.IntentReceiver;
import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

public class PushNotificationPlugin extends Plugin {
	final static String TAG = PushNotificationPlugin.class.getSimpleName();
	
	static PushNotificationPlugin instance = null;

	public static final String ACTION = "registerCallback";
	
	public PushNotificationPlugin() {
		instance = this;
	}
	
	public static PushNotificationPlugin getInstance() {
		return instance;
	}
	
	public void sendResultBack(String msg, String payload) {
		JSONObject data = new JSONObject();
		try {
			data.put("msg", msg);
			data.put("payload", payload);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		String js = String.format("window.plugins.pushNotification.notificationCallback('%s');", data.toString());
		this.sendJavascript(js);
	}
	
	public void sendApid(String apid){
		String js = String.format("window.plugins.pushNotification.getApid('%s');", apid);
		this.sendJavascript(js);
	}

	@Override
	public PluginResult execute(String action, JSONArray data,
			String callbackId) {
		
		PluginResult result = null;
		if (ACTION.equals(action)) {
			result = new PluginResult(Status.NO_RESULT);
			result.setKeepCallback(false);
		} else {
			Log.d(TAG, "Invalid action: " + action + " passed");
			result = new PluginResult(Status.INVALID_ACTION);
		}
		return result;
	}
}
