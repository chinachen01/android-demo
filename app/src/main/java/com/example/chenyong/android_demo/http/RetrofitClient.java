package com.example.chenyong.android_demo.http;


import android.support.annotation.NonNull;

import com.example.chenyong.android_demo.DemoApp;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * the single instance of retrofit.
 */
public final class RetrofitClient {
    private static Retrofit sRetrofit;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;
    //sBaseUrl 后面 必须后缀 '/'
    private static final String sBaseUrl = "https://api.github.com/";
    private static OkHttpClient sOkHttpClient;
    private RetrofitClient() {
        //打印请求Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存目录
        Cache cache = new Cache(DemoApp.sContext.getCacheDir(), 10 * 1024 * 1024);
        sOkHttpClient = new OkHttpClient.Builder()
                //打印请求log
                .addInterceptor(interceptor)
                //stetho,可以在chrome中查看请求,需要翻墙,不好用
                //.addNetworkInterceptor(new StethoInterceptor())
                //添加UA
                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                //mock数据
                .addInterceptor(new MockInterceptor())
                //设置token
                .addInterceptor(new TokenInterceptor())
                //必须是设置Cache目录
                .cache(cache)
                //走缓存，两个都要设置
                //.addInterceptor(new OnOffLineCachedInterceptor())
                //.addNetworkInterceptor(new OnOffLineCachedInterceptor())
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();

        sRetrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(sOkHttpClient)
                //baseUrl
                .baseUrl(sBaseUrl)
                //gson
                .addConverterFactory(GsonConverterFactory.create())
                //Rxandroid
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    @NonNull
    public static Retrofit getRetrofit() {
        return SingletonHolder.INSTANCE.sRetrofit;
    }
    @NonNull
    public static OkHttpClient getOkHttpClient() {
        return SingletonHolder.INSTANCE.sOkHttpClient;
    }
}
