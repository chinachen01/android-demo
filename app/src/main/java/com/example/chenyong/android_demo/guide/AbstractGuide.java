package com.example.chenyong.android_demo.guide;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.*;

import com.example.chenyong.android_demo.utils.SizeUtil;

/**
 * Created by focus on 16/12/2.
 */

public abstract class AbstractGuide implements IGuide {
    protected WindowManager windowManager;
    protected Activity context;
    protected View guideView;
    protected View.OnClickListener callback;

    public AbstractGuide(Activity context) {
        this.context = context;
        this.windowManager = context.getWindowManager();
        ViewConfiguration configuration = ViewConfiguration.get(context);
    }

    @Override
    public void addView(View view) {
        // 设置LayoutParams参数
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 设置显示格式
        params.format = PixelFormat.RGBA_8888;
        //不能枪战焦点
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 设置对齐方式
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        // 设置宽高
        params.width = SizeUtil.getDisplayMetrics(context).x;
        params.height = SizeUtil.getDisplayMetrics(context).y/5;
        // 添加到当前的窗口上
        guideView = view;
        windowManager.addView(guideView, params);
//        StatusBarUtil.setColor(context, ResourceUtils.getColor(R.color.guide_background));
    }

    @Override
    public void removeView() {
        if (guideView != null) {
//            StatusBarUtil.setColor(context, ResourceUtils.getColor(R.color.colorPrimary));
            windowManager.removeView(guideView);
            guideView = null;
        }
    }

    @Override
    public void setCallback(View.OnClickListener callback) {
        this.callback = callback;
    }
}
