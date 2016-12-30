package com.example.chenyong.android_demo.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 16/12/28.
 */

public final class ActivityCollector {
    public static List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static Activity getTopActivity() {
        if (sActivities.isEmpty()) {
            return null;
        }
        return sActivities.get(sActivities.size() - 1);
    }
}
