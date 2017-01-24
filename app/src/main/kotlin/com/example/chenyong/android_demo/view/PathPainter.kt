package com.example.chenyong.android_demo.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View

/**
 * Created by focus on 17/1/24.
 */
class PathPainter(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context?) : this(context, null, 0)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    val pathMeasure: PathMeasure
    val paint: Paint
    val path: Path
    val dstPath: Path
    val length: Float
    var animatorValue: Float = 0f

    init {
        pathMeasure = PathMeasure()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        path = Path()
        path.addCircle(400f, 400f, 100f, Path.Direction.CW)
        pathMeasure.setPath(path, true)
        length = pathMeasure.length
        dstPath = Path()
        var valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener { animator ->
            kotlin.run {
                animatorValue = animator.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator.duration = 2000
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dstPath.reset()
        //硬件加速bug
        dstPath.moveTo(0f, 0f)
        var stop = length * animatorValue
        pathMeasure.getSegment(0f, stop, dstPath, false)
        canvas?.drawPath(dstPath, paint)
    }
}