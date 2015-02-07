package com.numan1617.bournemouthlocations.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Constructor;


public final class UserAgentHelper {

    private UserAgentHelper() {
    }

    public static String getDefaultUserAgentString(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            return getDefaultUserAgent(context);
        }

        try {
            Constructor<WebSettings> constructor = WebSettings.class
                    .getDeclaredConstructor(Context.class, WebView.class);
            constructor.setAccessible(true);
            try {
                WebSettings settings = constructor.newInstance(context, null);
                return settings.getUserAgentString();
            } finally {
                constructor.setAccessible(false);
            }
        } catch (Exception e) {
            return new WebView(context).getSettings().getUserAgentString();
        }
    }

    @TargetApi(17)
    static String getDefaultUserAgent(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

}
