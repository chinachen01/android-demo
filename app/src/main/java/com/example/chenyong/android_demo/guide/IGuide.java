package com.example.chenyong.android_demo.guide;

import android.view.View;

/**
 * Created by focus on 16/12/2.
 */

public interface IGuide {
    void addView(View view);

    void removeView();

    /**
     * 在addView之前调用该方法.
     * @param callback callback
     */
    void setCallback(View.OnClickListener callback);
}
