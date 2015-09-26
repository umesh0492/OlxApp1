package com.rey.material.ui;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rey.material.MaterialUIMainApplication;


public class TypefaceTextView extends TextView {

    public TypefaceTextView(final Context context) {
        super(context);
        TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
        this.setTransformationMethod(null);
    }

    public TypefaceTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypefaceTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        TypefaceUtil.setFace(this, context, attrs);
        //TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);

    }
}