package com.example.chenyong.android_demo.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.chenyong.android_demo.APIservice
import com.example.chenyong.android_demo.R
import com.example.chenyong.android_demo.databinding.ActivityHttpBinding
import com.example.chenyong.android_demo.http.RetrofitClient
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by focus on 17/1/18.
 */
class HttpActivity : BaseActivity() {
    lateinit var dataBinding: ActivityHttpBinding
    lateinit var api: APIservice
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_http)
        api = RetrofitClient.getRetrofit().create(APIservice::class.java)
        normalObserval()
        dataBinding.presenter = Presenter()
    }

    private fun showUser() {
        api.getUserInfo("chinachen01asdd")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({user -> Log.d(TAG, "user: $user")}
                ,{err -> Log.d(TAG, "error: ${err.message}")})
    }

    private fun watchRepo() {
        api.watchRepo("ReactiveX", "RxAndroid")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<Any>() {
                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted: true")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "onError: true")
                    }

                    override fun onNext(t: Any?) {
                        dataBinding.info.text = "watch success"
                    }

                })
    }

    private fun stopWatchRepo() {
        api.stopWatchRepo("ReactiveX", "RxAndroid")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<Any>() {
                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted: true")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "onError: true")
                    }

                    override fun onNext(t: Any?) {
                        dataBinding.info.text = "stop watch success"
                    }

                })
    }

    private fun normalObserval() {
        var obs = Observable.create<String> { subs ->
            kotlin.run {
                //dosomething
                subs.onNext("123")
                Thread.sleep(3000)
                subs.onNext("345")
                Thread.sleep(3000)
                subs.onNext("567")
                Thread.sleep(3000)
                subs.onNext("789")
                subs.onCompleted()
            }
        }
        obs.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({string-> Log.d(TAG, "string: $string")},
                        {error -> Log.d(TAG, "error: $error")})
    }

    inner class Presenter {
        fun onClick(view: View) {
            when (view.id) {
                R.id.show_user -> showUser()
                R.id.watch -> watchRepo()
                R.id.stop_watch -> stopWatchRepo()
            }
        }
    }
}