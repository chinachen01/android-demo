package com.example.chenyong.android_demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chenyong.android_demo.R;

/**
 * Created by chenyong on 16-12-21.
 */

public class PropertyAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.meinv:
                break;
            default:
                break;
        }
    }
}
