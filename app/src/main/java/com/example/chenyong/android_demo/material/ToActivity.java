package com.example.chenyong.android_demo.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;

import com.example.chenyong.android_demo.R;

/**
 * Created by focus on 17/4/14.
 */

public class ToActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
        setWindowAnimation();
    }

    private void setWindowAnimation() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(fade);
        getWindow().setEnterTransition(slide);
    }
}
