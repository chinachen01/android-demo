package com.example.chenyong.android_demo

/**
 * Created by focus on 17/1/18.
 */
class AlgorithmImpl {
    /**
     * 计算两个非负整数 p 和 q 的最大公约数:若 q 是 0,则最大公约数为 p。否则,将 p 除以 q得到余数r,p和q的最大公约数即为q和 r 的最大公约数。
     */
    fun gcd(p: Int, q: Int): Int {
        if (q == 0) return p
        else {
            val r = p % q
            return gcd(q, r)
        }
    }

    /**
     * 编写递归代码的重要三点：
     * 1.递归总有一个最简单的情况--方法的第一条语句总是一个包含return的条件语句
     * 2.递归调用总是去尝试解决一个规模更小的子问题，这样递归才能收敛到最简单的情况
     *   在下面的代码中，第四个参数和但三个参数的差值一直在缩小
     * 3.递归调用的父问题和尝试解决的子问题之间不应该有交集
     *   在代码中，两个子问题各自操作的数组部分是不同的
     */
    fun rank(key: Int, a: IntArray, lo: Int = 0, hi: Int = a.size): Int {
        if (lo > hi) return -1
        val mid = lo + (hi - lo) / 2
        if (key < a[mid]) return rank(key, a, lo, mid - 1)
        else if (key > a[mid]) return rank(key, a, mid + 1, hi)
        else return mid
    }
}