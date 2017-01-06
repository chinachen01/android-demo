package com.example.chenyong.android_demo;

import android.app.Application;

import com.example.chenyong.android_demo.dao.DaoMaster;
import com.example.chenyong.android_demo.dao.DaoSession;
import com.example.chenyong.android_demo.dao.MyDataBaseOpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by focus on 17/1/5.
 */

public class DemoApp extends Application {
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBaseOpenHelper helper = new MyDataBaseOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
