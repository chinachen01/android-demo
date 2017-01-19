package com.example.chenyong.android_demo.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.chenyong.android_demo.R
import com.example.chenyong.android_demo.databinding.ActivityAlgorithmBinding

/**
 * Created by focus on 17/1/18.
 */
class AlgorithmActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding:ActivityAlgorithmBinding = DataBindingUtil.setContentView(this, R.layout.activity_algorithm)
    }

}

