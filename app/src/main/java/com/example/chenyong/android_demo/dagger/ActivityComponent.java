package com.example.chenyong.android_demo.dagger;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.chenyong.android_demo.activity.GreenDaoActivity;
import com.example.chenyong.android_demo.activity.InjectActivity;

import dagger.Component;

/**
 * Created by focus on 17/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModules.class)
public interface ActivityComponent {
    void inject(InjectActivity activity);

    void inject(GreenDaoActivity activity);
    Activity activity();
    @SpQualifier("share1")
    SharedPreferences getSharedPreferences();
    @SpQualifier("share2")
    SharedPreferences getSharedPreferences2();

}
