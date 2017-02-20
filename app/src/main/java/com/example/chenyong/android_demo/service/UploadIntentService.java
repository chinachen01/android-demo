package com.example.chenyong.android_demo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.chenyong.android_demo.activity.IntentServiceActivity;

/**
 * Created by focus on 17/2/20.
 */

public class UploadIntentService extends IntentService {
    public static final String UPLOAD_ACTION = "com.focus.upload";
    public static final String PATH = "com.focus.upload.path";
    public UploadIntentService() {
        super("upload-service");
    }

    public static void startUpload(String path, Context context) {
        Intent intent = new Intent(context, UploadIntentService.class);
        intent.setAction(UPLOAD_ACTION);
        intent.putExtra(PATH, path);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(UPLOAD_ACTION)) {
                uploadImg(intent.getStringExtra(PATH));
            }
        }
    }

    private void uploadImg(String path) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setAction(IntentServiceActivity.UPLOAD_RESULT);
        intent.putExtra(PATH, path);
        sendBroadcast(intent);
    }
}
