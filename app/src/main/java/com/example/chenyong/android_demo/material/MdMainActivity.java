package com.example.chenyong.android_demo.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.chenyong.android_demo.R;

/**
 * Created by focus on 17/4/11.
 */

public class MdMainActivity extends AppCompatActivity {
    private Button mButtonFrom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_main);
        mButtonFrom = (Button) findViewById(R.id.button_from);
        mButtonFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MdMainActivity.this, ToActivity.class));
            }
        });
        setUpAnimations();
    }

    private void setUpAnimations() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(fade);
        getWindow().setEnterTransition(slide);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_explode:

                break;
            case R.id.action_fade:

                break;
            case R.id.action_slid:

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.md_main, menu);
        return true;
    }

}
