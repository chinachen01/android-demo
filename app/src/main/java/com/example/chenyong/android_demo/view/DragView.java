package com.example.chenyong.android_demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by focus on 16/12/30.
 */

public class DragView extends View{
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;
    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastX = x;
//                mLastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int xOffset = x - mLastX;
//                int yOffset = y - mLastY;
//                layout(getLeft() + xOffset,
//                        getTop() + yOffset,
//                        getRight() + xOffset,
//                        getBottom() + yOffset);
//                mLastX = x;
//                mLastY = y;
//                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
