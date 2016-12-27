package com.example.chenyong.android_demo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.databinding.ActivityCustomViewBinding;

/**
 * Created by focus on 16/12/27.
 */

public class CustomViewActivity extends AppCompatActivity{
    private ActivityCustomViewBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_view);
    }
}
