package com.rey.material.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.rey.material.MaterialUIMainApplication;
import com.rey.material.demo.R;


public class TypefaceUtil {
    public static void setFace(TextView view, Context context, AttributeSet attrs) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Typeface);
        if (array != null) {
            String typefaceAssetPath = array.getString(
                    R.styleable.Typeface_customTypeface);

            if (typefaceAssetPath == null) {
                //default font
                typefaceAssetPath = MaterialUIMainApplication.DEFAULT_FONT;
            }
            else {
                Log.d("typefaceAssetPath " +context.getClass().getSimpleName(), typefaceAssetPath);

            }
            setTypography(view, context, typefaceAssetPath);

            array.recycle();
        }

    }

    public static void setTypography(TextView view, Context context, String typefaceAssetPath) {
        Typeface typeface = null;

        if (MaterialUIMainApplication.getTypefaces().containsKey(typefaceAssetPath)) {
            typeface = MaterialUIMainApplication.getTypefaces().get(typefaceAssetPath);
        } else {
            AssetManager assets = context.getAssets();
            typeface = Typeface.createFromAsset(assets, typefaceAssetPath);
            MaterialUIMainApplication.getTypefaces().put(typefaceAssetPath, typeface);
        }

        view.setTypeface(typeface);
    }
}
