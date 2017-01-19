package com.example.chenyong.android_demo.dagger;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by focus on 17/1/19.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return mApplication;
    }
    @Provides @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) mApplication.getSystemService(Context.LOCATION_SERVICE);
    }
}
