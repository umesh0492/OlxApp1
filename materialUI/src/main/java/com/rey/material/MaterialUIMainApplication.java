package com.rey.material;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarandeep on 9/4/14.
 */
public class MaterialUIMainApplication extends Application {
    //public static final String DEFAULT_FONT = "titillium-regular.ttf";
    public static final String DEFAULT_FONT = "Roboto-Regular.ttf";

    private static final String TAG = "Redcarpet";
    public static Typeface ICON_FONT = null;
    public static Typeface ICON_FONT_STREAMLINE = null;
    private static DisplayMetrics displayMetrics;


    private static Map<String, Typeface> sTypefaces = new HashMap<String, Typeface>();

    public static int getDpForPixels(int pixels) {
        return Math.round(pixels * displayMetrics.density);
    }

    public static Map<String, Typeface> getTypefaces() {
        return sTypefaces;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "SDK started");
       // ThemeManager.init(this, 1, 0, null);
        ICON_FONT = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        ICON_FONT_STREAMLINE = Typeface.createFromAsset(getAssets(), "streamline-24px.ttf");
        displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

    }


}
