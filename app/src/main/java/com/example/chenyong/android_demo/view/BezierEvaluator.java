package com.example.chenyong.android_demo.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.example.chenyong.android_demo.utils.BezierUtil;

/**
 * Created by focus on 17/2/14.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF mContorlPonintF;
    public BezierEvaluator(PointF controlPointF) {
        this.mContorlPonintF = controlPointF;
    }
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(fraction, startValue, mContorlPonintF, endValue);
    }
}
