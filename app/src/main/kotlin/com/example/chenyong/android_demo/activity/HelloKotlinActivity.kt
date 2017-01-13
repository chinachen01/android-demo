package com.example.chenyong.android_demo.activity

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.chenyong.android_demo.R
import com.example.chenyong.android_demo.databinding.ActivityHelloKotlinBinding

class HelloKotlinActivity : BaseActivity() {
    var binding: ActivityHelloKotlinBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hello_kotlin)
        var presenter = Presenter()
        binding?.presenter = presenter
        binding?.toast?.setOnClickListener({ toast("onClick")})
    }

    fun testNullable() {
        var a:String? = "abc"
        a = null
        Log.d(TAG, "length:" + a?.length)
        Log.d(TAG, "length:" + if(a!=null) a.length else -1)
        Log.d(TAG, "length:" + a?.length ?: "-1")
        //var l = a!!.length
        var activity: Activity? = a as? Activity
        Log.d(TAG, "activity:" + activity)
        var nullableList: List<Int?> = listOf(1, 2, null, 4)
        var intList: List<Int> = nullableList.filterNotNull()
        for (item in intList) {
            Log.d(TAG, "item:" + item)
        }
        var numbers = listOf(1, 2, 3)
        Log.d(TAG, "numbers1: $numbers")
        var newNumbers = numbers.filter { num -> num%2 !=0 }
        Log.d(TAG, "numbers2: $newNumbers")
        Log.d(TAG, newNumbers.joinToString(prefix = "[",postfix = "]"))
        numbers.any { it -> it%2==0}
    }
    inner class Presenter {
        fun onClick(view: View) {
            when (view.id) {
                R.id.nullable -> testNullable()
                else -> Log.d(TAG, "do nothing")
            }
        }
    }

    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(applicationContext, message, duration).show()
    }
}
