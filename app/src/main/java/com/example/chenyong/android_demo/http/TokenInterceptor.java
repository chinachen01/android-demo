package com.example.chenyong.android_demo.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by focus on 17/1/18.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .addHeader("Authorization", "Basic Y2hpbmFjaGVuMDE6ZTZkNGE1ZDJiN2FmZDY1NDE4ZDhiY2ZhNTJjODEyY2E2YzU2ZTA4NA==");
        Request request = builder.build();
        return chain.proceed(request);
    }
}
