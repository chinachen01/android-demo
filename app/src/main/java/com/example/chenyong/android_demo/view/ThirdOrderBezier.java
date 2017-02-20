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

public class ThirdOrderBezier extends View {
    private static final String TAG = "Bezier";
    private Paint mLinePaint; //辅助线
    private Paint mBezierPaint; //贝塞尔曲线
    private Paint mTextPaint;
    private int mControlX1; //控制点1
    private int mControlY1;
    private int mControlX2; //控制点2
    private int mControlY2;
    private int mStartX; //起始点
    private int mStartY;
    private int mEndX; //结束点
    private int mEndY;
    private boolean mIsSecondPotin;
    private Path mLinePath;
    private Path mBezierPath;

    public ThirdOrderBezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThirdOrderBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(4.0f);
        mLinePaint.setColor(getResources().getColor(android.R.color.black));
        mTextPaint = new Paint();
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(getResources().getColor(android.R.color.black));

        mBezierPaint = new Paint();
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setStrokeWidth(8.0f);
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
        mControlX1 = w / 4;
        mControlY1 = h / 4;
        mControlX2 = w / 4*3;
        mControlY2 = h / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: draw");
        canvas.drawText("起始点", mStartX, mStartY, mTextPaint);
        canvas.drawText("结束点", mEndX, mEndY, mTextPaint);
        canvas.drawText("控制点1", mControlX1, mControlY1, mTextPaint);
        canvas.drawText("控制点2", mControlX2, mControlY2, mTextPaint);
        mLinePath.reset();
        mLinePath.moveTo(mStartX, mStartY);
        mLinePath.lineTo(mControlX1, mControlY1);
        mLinePath.lineTo(mControlX2, mControlY2);
        mLinePath.lineTo(mEndX, mEndY);
        canvas.drawPath(mLinePath, mLinePaint);

        mBezierPath.reset();
        mBezierPath.moveTo(mStartX, mStartY);
        mBezierPath.cubicTo(mControlX1, mControlY1, mControlX2, mControlY2, mEndX, mEndY);
        canvas.drawPath(mBezierPath, mBezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                mIsSecondPotin = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mIsSecondPotin = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mControlX1 = (int) event.getX(0);
                mControlY1 = (int) event.getY(0);
                if (mIsSecondPotin) {
                    mControlX2 = (int) event.getX(1);
                    mControlY2 = (int) event.getY(1);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        return true;
    }
}
