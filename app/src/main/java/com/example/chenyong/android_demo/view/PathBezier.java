package com.example.chenyong.android_demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.chenyong.android_demo.R;

/**
 * Created by focus on 17/2/14.
 */

public class PathBezier extends View implements View.OnClickListener{
    private Paint mCirclePaint;
    private Paint mBitPaint;
    private Paint mPathPaint;
    private Path mBezierPath;
    private float mStartX = 200f;
    private float mStartY = 200f;
    private float mEndX = 600f;
    private float mEndY = 1000f;
    private float mControlX = 600f;
    private float mControlY = 200f;
    private float mCurrentX = 200f;
    private float mCurrentY = 200f;
    private Bitmap mBitmap;
    public PathBezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(4f);
        mPathPaint.setColor(getResources().getColor(android.R.color.black));
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(getResources().getColor(android.R.color.black));
        mBitPaint = new Paint();
        mBitPaint.setAntiAlias(true);

        mBezierPath = new Path();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartX, mStartY, 20, mCirclePaint);
        canvas.drawCircle(mEndX, mEndY, 20, mCirclePaint);
        mBezierPath.reset();
        mBezierPath.moveTo(mStartX, mStartY);
        mBezierPath.quadTo(mControlX, mControlY, mEndX, mEndY);
        canvas.drawPath(mBezierPath, mPathPaint);

//        canvas.drawCircle(mCurrentX, mCurrentY, 20, mCirclePaint);
        canvas.drawBitmap(mBitmap,mCurrentX, mCurrentY, mBitPaint);
    }


    @Override
    public void onClick(View v) {
        BezierEvaluator bezierEvaluator = new BezierEvaluator(new PointF(mControlX, mControlY));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierEvaluator, new PointF(mStartX, mStartY), new PointF(mEndX, mEndY));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mCurrentX = pointF.x;
                mCurrentY = pointF.y;
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }
}
