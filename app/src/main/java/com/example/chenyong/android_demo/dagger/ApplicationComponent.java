package com.example.chenyong.android_demo.dagger;

import android.app.Application;
import android.location.LocationManager;

import com.example.chenyong.android_demo.DemoApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by focus on 17/1/19.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DemoApp application);

    Application application();
    LocationManager locationManager();
}
