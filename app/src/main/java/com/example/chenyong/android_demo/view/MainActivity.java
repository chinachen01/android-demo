package com.example.chenyong.android_demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chenyong.android_demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.property:
                startActivity(new Intent(this, PropertyAnimationActivity.class));
                break;
            case R.id.custom_view:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
            default:
                break;
        }
    }
}
