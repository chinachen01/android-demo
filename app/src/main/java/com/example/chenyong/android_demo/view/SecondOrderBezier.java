package com.example.chenyong.android_demo.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔曲线.
 */

public class SecondOrderBezier extends View {
    private static final String TAG = "Bezier";
    private Paint mLinePaint; //辅助线
    private Paint mBezierPaint; //贝塞尔曲线
    private Paint mTextPaint;
    private int mControlX; //控制点
    private int mControlY;
    private int mStartX; //起始点
    private int mStartY;
    private int mEndX; //结束点
    private int mEndY;

    private Path mLinePath;
    private Path mBezierPath;

    public SecondOrderBezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondOrderBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2.0f);
        mLinePaint.setColor(getResources().getColor(android.R.color.black));
        mTextPaint = new Paint();
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(getResources().getColor(android.R.color.black));

        mBezierPaint = new Paint();
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setStrokeWidth(4.0f);
        mBezierPaint.setColor(getResources().getColor(android.R.color.holo_red_light));

        mLinePath = new Path();
        mBezierPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: size");
        mStartX = w / 4;
        mStartY = h / 2;
        mEndX = w / 4 * 3;
        mEndY = h / 2;
        mControlX = w / 2;
        mControlY = h / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: draw");
        canvas.drawText("起始点", mStartX, mStartY, mTextPaint);
        canvas.drawText("结束点", mEndX, mEndY, mTextPaint);
        canvas.drawText("控制点", mControlX, mControlY, mTextPaint);
        mLinePath.reset();
        mLinePath.moveTo(mStartX, mStartY);
        mLinePath.lineTo(mControlX, mControlY);
        mLinePath.lineTo(mEndX, mEndY);
        canvas.drawPath(mLinePath, mLinePaint);

        mBezierPath.reset();
        mBezierPath.moveTo(mStartX, mStartY);
        mBezierPath.quadTo(mControlX, mControlY, mEndX, mEndY);
        canvas.drawPath(mBezierPath, mBezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mControlX = (int) event.getX();
                mControlY = (int) event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
