package com.sampleProject;

import com.phonegap.DroidGap;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

import android.webkit.WebView;

//developed by Ilker Guller - developerarea.blogspot.com

public class sendJavascript {
    private WebView mAppView;
    private DroidGap mGap;

    public sendJavascript(DroidGap gap, WebView view)
    {
        mAppView = view;
        mGap = gap;
    }

    public String getApid(){
    	PushPreferences prefs = PushManager.shared().getPreferences();
        return prefs.getPushId();
    }
}