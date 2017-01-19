package com.example.chenyong.android_demo.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by focus on 17/1/19.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SpQualifier {
    String value() default "";
}
