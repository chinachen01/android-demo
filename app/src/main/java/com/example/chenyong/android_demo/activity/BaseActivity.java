package com.example.chenyong.android_demo.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chenyong.android_demo.inter.PermissionCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 16/12/28.
 */

public class BaseActivity extends AppCompatActivity {

    public static PermissionCallback sPermissionCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void requestPermissions(List<String> permissions, PermissionCallback callback) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (sPermissionCallback == null) {
            return;
        }
        List<String> deniedPermissions = new ArrayList<>();
        if (grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }
        }
        if (!deniedPermissions.isEmpty()) {
            sPermissionCallback.onDenied(deniedPermissions);
        } else {
            sPermissionCallback.onGranted();
        }
    }
}
