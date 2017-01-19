package com.example.chenyong.android_demo

import com.example.chenyong.android_demo.model.GitUser
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import rx.Observable

/**
 * Created by focus on 17/1/18.
 */
interface APIservice {
    @GET("/users/{user}")
    fun getUserInfo(@Path("user") user:String):Observable<GitUser>
    @DELETE("/user/subscriptions/{owner}/{repo}")
    fun stopWatchRepo(@Path("owner") owner:String, @Path("repo") repo:String):Observable<Any>
    @PUT("/user/subscriptions/{owner}/{repo}")
    fun watchRepo(@Path("owner") owner:String, @Path("repo") repo:String):Observable<Any>
}