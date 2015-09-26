package com.rey.material.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.rey.material.MaterialUIMainApplication;


public class TypefaceButton extends Button {

    public TypefaceButton(final Context context) {
        super(context);
        TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
        this.setTransformationMethod(null);
    }

    public TypefaceButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        TypefaceUtil.setFace(this, context, attrs);
        TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
        this.setTransformationMethod(null);
    }

    public TypefaceButton(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        TypefaceUtil.setFace(this, context, attrs);
        this.setTransformationMethod(null);
    }

}