package com.example.chenyong.android_demo.antotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * android因避免使用枚举
 */

public class Game {
    //先定义常量
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({SUNDAY, MONDAY, TUESDAY, WEDNESDAY,THURSDAY, FRIDAY, SATURDAY})
    public @interface WeedDays {

    }

    public void setCurrentDay(@WeedDays int day) {

    }
}
