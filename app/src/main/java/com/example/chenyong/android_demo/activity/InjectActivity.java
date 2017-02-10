package com.example.chenyong.android_demo.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chenyong.android_demo.DemoApp;
import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.antotation.Game;
import com.example.chenyong.android_demo.dagger.*;

import javax.inject.Inject;

/**
 * Created by focus on 17/1/19.
 */

public class InjectActivity extends BaseActivity {
    @Inject
    Activity mActivity;
    @Inject @SpQualifier("share1")
    SharedPreferences mSharedPreferences;
    @Inject @SpQualifier("share2")
    SharedPreferences mSharedPreferences2;
    @Inject
    LocationManager mLocationManager;
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((DemoApp)getApplication()).getApplicationComponent())
                .activityModules(new ActivityModules(this))
                .build();
        mActivityComponent.inject(this);
        Log.d(TAG, "onCreate: " + mActivity);
        Log.d(TAG, "onCreate mSharedPreferences: " + mSharedPreferences);
        Log.d(TAG, "onCreate: " + mLocationManager);
        Log.d(TAG, "onCreate mSharedPreferences2: " + mSharedPreferences2);
        setCurrentDay(1);
    }

    private void setCurrentDay(@Game.WeedDays int currentDay) {

    }
}
