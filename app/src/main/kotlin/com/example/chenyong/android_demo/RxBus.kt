package com.example.chenyong.android_demo

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

/**
 * Created by focus on 17/1/23.
 */
object RxBus {
    private val _bus: Subject<Any, Any> = SerializedSubject(PublishSubject.create())
    fun send(o: Any) {
        _bus.onNext(o)
    }

    fun toObserverable(): Observable<Any> {
        return _bus
    }

}