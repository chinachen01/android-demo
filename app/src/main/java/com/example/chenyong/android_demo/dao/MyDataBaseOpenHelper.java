package com.example.chenyong.android_demo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by focus on 17/1/6.
 */

public class MyDataBaseOpenHelper extends DaoMaster.OpenHelper {
    public MyDataBaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        for (int migrateVersion = oldVersion + 1; migrateVersion <= newVersion; migrateVersion++)
            upgrade(db, migrateVersion);
    }
    /**
     * in case of android.database.sqlite.SQLiteException, the schema version is
     * left untouched just fix the code in the version case and push a new
     * release
     *
     * @param db
     * @param migrateVersion
     */
    private void upgrade(SQLiteDatabase db, int migrateVersion) {
        switch (migrateVersion) {
            case 2:
//                db.execSQL("ALTER TABLE INHABITANT ADD COLUMN 'GENDER' INTEGER NOT NULL DEFAULT '0';");
                Log.d("database", "do version 2");
                break;
            case 3:
//                db.execSQL("ALTER TABLE INHABITANT ADD COLUMN 'SPECIES' TEXT;");
//                db.execSQL("ALTER TABLE INVERTEBRATE ADD COLUMN 'SPECIES' TEXT;");
//                db.execSQL("ALTER TABLE PLANT ADD COLUMN 'SPECIES' TEXT;");
//                db.execSQL("ALTER TABLE CORAL ADD COLUMN 'SPECIES' TEXT;");
                Log.d("database", "do version 3");
                break;
            case 4:
//                db.execSQL("ALTER TABLE NOTE ADD COLUMN 'TYPE' TEXT;");
                break;
        }
    }
}
