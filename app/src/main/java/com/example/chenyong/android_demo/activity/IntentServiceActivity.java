package com.example.chenyong.android_demo.activity;

import android.content.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.service.UploadIntentService;

/**
 * Created by focus on 17/2/20.
 */

public class IntentServiceActivity extends AppCompatActivity {
    private LinearLayout mTaskContainer;
    private Button mAddBtn;
    private int mPathCount = 1;
    public static final String UPLOAD_RESULT = "com.focus.upload_result";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String path = intent.getStringExtra(UploadIntentService.PATH);
                TextView tv = (TextView) mTaskContainer.findViewWithTag(path);
                if (tv != null) {
                    tv.setText(path + ".png upload suc~~~~");
                }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        mTaskContainer = (LinearLayout) findViewById(R.id.intent_service_task_container);
        registerReceiver(mBroadcastReceiver, new IntentFilter(UPLOAD_RESULT));
    }

    public void onClick(View view) {
        TextView textView = new TextView(this);
        String path = "/sdcard/imgs/" + mPathCount++;
        textView.setText(path + ".png uploading....");
        mTaskContainer.addView(textView);
        textView.setTag(path);
        UploadIntentService.startUpload(path, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
