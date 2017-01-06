package com.example.chenyong.android_demo.utils;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.chenyong.android_demo.activity.ActivityCollector;
import com.example.chenyong.android_demo.activity.BaseActivity;
import com.example.chenyong.android_demo.inter.PermissionCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 16/12/29.
 */

public final class PermissionUtil {
    private static final int PERMISSION_CODE = 117;
    private PermissionUtil() {

    }
    public static void requestPermission(List<String> permissions, PermissionCallback callback) {
        if (callback == null || permissions.isEmpty()) {
            return;
        }
        BaseActivity baseActivity = (BaseActivity) ActivityCollector.getTopActivity();
        baseActivity.mPermissionCallback = callback;
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(baseActivity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        if (deniedPermissions.isEmpty()) {
            callback.onGranted();
        } else {
            ActivityCompat.requestPermissions(baseActivity,
                    deniedPermissions.toArray(new String[]{}), PERMISSION_CODE);
        }
    }
}
