package com.example.chenyong.android_demo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.databinding.ActivityScrollerBinding;

/**
 * Created by focus on 16/12/28.
 */

public class ScrollerActivity extends AppCompatActivity {
    private ActivityScrollerBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_scroller);
    }
}
