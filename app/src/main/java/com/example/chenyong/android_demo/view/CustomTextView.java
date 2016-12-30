package com.example.chenyong.android_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.chenyong.android_demo.R;

/**
 * Created by focus on 16/12/28.
 */

public class CustomTextView extends TextView {
    private Paint mBordPaint;
    private Paint mBgPaint;
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBordPaint = new Paint();
        mBordPaint.setStyle(Paint.Style.FILL);
        mBordPaint.setColor(getResources().getColor(R.color.colorYellow));
        mBgPaint = new Paint();
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(getResources().getColor(R.color.colorAccent));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制外层矩形
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mBordPaint);
        //绘制内层矩形
        canvas.drawRect(10,10,getMeasuredWidth() -10, getMeasuredHeight() - 10, mBgPaint);
        //在移动画布前需要save
        canvas.save();
        canvas.translate(10, 10);
        super.onDraw(canvas);
        canvas.restore();
    }

}
