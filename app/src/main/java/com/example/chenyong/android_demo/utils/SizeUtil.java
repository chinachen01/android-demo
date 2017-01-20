package com.example.chenyong.android_demo.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.PowerManager;
import android.view.WindowManager;

/**
 * Created by focus on 17/1/20.
 */

public final class SizeUtil {
    private static Point point;
    private static Float scale;
    public static PowerManager.WakeLock mWakeLock;

    private SizeUtil() {

    }
    public static int getHeightByFixedWidth(float scale, Context context) {
        Point point = getDisplayMetrics(context);
        return (int)((float)point.x * scale);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static Point getDisplayMetrics(Context context) {
        if(context == null) {
            return new Point();
        } else {
            WindowManager wm = (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            if(point == null) {
                point = new Point();
                wm.getDefaultDisplay().getSize(point);
            }
            return point;
        }
    }

    public static int dpToPixel(int dp, Context context) {
        if(scale == null) {
            scale = Float.valueOf(context.getResources().getDisplayMetrics().density);
        }

        return (int)((float)dp * scale.floatValue());
    }

    public static int spToPixel(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }
}
