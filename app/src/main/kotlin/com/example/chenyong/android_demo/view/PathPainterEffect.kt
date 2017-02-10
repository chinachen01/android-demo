package com.example.chenyong.android_demo.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator

/**
 * Created by focus on 17/2/6.
 */
class PathPainterEffect(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr),View.OnClickListener{
    override fun onClick(v: View?) {
        animator.start()
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null)

    val pathMeasure = PathMeasure()
    val path = Path()
    val paint = Paint()
    val animator:ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
    init {
        path.reset()
        path.moveTo(100f, 100f)
        path.lineTo(100f, 500f)
        path.lineTo(300f, 300f)
        path.lineTo(100f, 100f)
        path.close()
        pathMeasure.setPath(path, true)
        val length = pathMeasure.length
        animator.interpolator = AccelerateInterpolator()
        animator.addUpdateListener { animator -> kotlin.run {
            var fraction = animator.animatedValue as Float
            var dashEffect = DashPathEffect(floatArrayOf(length,length),fraction)
            paint.setPathEffect(dashEffect)
            invalidate()
        } }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }
}