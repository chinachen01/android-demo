package com.example.chenyong.android_demo.dagger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by focus on 17/1/19.
 */
@Module
public class ActivityModules {
    private final Activity mActivity;

    public ActivityModules(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    @SpQualifier("share1")
    SharedPreferences provideSharedPreferences1() {
        return mActivity.getSharedPreferences("share1", Context.MODE_PRIVATE);
    }
    @Provides
    @SpQualifier("share2")
    SharedPreferences provideSharedPreferences2() {
        return mActivity.getSharedPreferences("share2", Context.MODE_PRIVATE);
    }

}
