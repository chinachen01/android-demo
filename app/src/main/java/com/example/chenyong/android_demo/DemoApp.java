package com.example.chenyong.android_demo;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.example.chenyong.android_demo.dagger.ApplicationComponent;
import com.example.chenyong.android_demo.dagger.ApplicationModule;
import com.example.chenyong.android_demo.dagger.DaggerApplicationComponent;
import com.example.chenyong.android_demo.dao.DaoMaster;
import com.example.chenyong.android_demo.dao.DaoSession;
import com.example.chenyong.android_demo.dao.MyDataBaseOpenHelper;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by focus on 17/1/5.
 */

public class DemoApp extends Application {
    private DaoSession mDaoSession;
    public static Context sContext;
    private ApplicationComponent mApplicationComponent;
    @Inject
    LocationManager mLocationManager;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        MyDataBaseOpenHelper helper = new MyDataBaseOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
        Log.d(TAG, "onCreate: " + mLocationManager);
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
