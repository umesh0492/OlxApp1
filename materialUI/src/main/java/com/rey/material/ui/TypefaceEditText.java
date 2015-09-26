package com.rey.material.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.rey.material.MaterialUIMainApplication;


public class TypefaceEditText extends EditText {

    public TypefaceEditText(final Context context) {
        super(context);
        TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
    }

    public TypefaceEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        TypefaceUtil.setFace(this, context, attrs);
    }

    public TypefaceEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        TypefaceUtil.setFace(this, context, attrs);
        TypefaceUtil.setTypography(this, context, MaterialUIMainApplication.DEFAULT_FONT);
    }


}