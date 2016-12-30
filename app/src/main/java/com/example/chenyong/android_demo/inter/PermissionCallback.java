package com.example.chenyong.android_demo.inter;

import java.util.List;

/**
 * Created by focus on 16/12/28.
 */

public interface PermissionCallback {
    void onGranted();

    void onDenied(List<String> denies);
}
