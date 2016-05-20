package com.edu.feicui.safeapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 16245 on 2016/05/15.
 */
public class FocusedText extends TextView{
    public FocusedText(Context context) {
        super(context);
    }

    public FocusedText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusedText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
