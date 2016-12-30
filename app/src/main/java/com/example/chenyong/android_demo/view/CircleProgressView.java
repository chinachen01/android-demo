package com.example.chenyong.android_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.chenyong.android_demo.R;

/**
 * Created by focus on 16/12/28.
 */

public class CircleProgressView extends View {
    private Paint mPaint;
    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int circleX= width /2;
        int circleY= height /2;
        int radius = circleX/4;
        canvas.drawCircle(circleX, circleY,radius, mPaint);
        RectF arcRectF = new RectF((float) (width * 0.1), (float)(width * 0.1), (float)(width * 0.9), (float)(width * 0.9));
        canvas.drawArc(arcRectF, 270, 30, false, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
