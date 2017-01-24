package com.example.chenyong.android_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;

import com.example.chenyong.android_demo.*;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RxBus.INSTANCE.toObserverable().subscribe((event) -> Log.d(TAG, "onCreate: " + ((TapEvent) event).getName()));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_view:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.scroller:
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
                break;
            case R.id.device:
                startActivity(new Intent(MainActivity.this, DeviceActivity.class));
                break;
            case R.id.dao:
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                break;
            case R.id.hello_kotlin:
                startActivity(new Intent(MainActivity.this, HelloKotlinActivity.class));
                break;
            case R.id.webview:
                startActivity(new Intent(MainActivity.this, WebActivity.class));
                break;
            case R.id.http:
                startActivity(new Intent(MainActivity.this, HttpActivity.class));
                break;
            case R.id.algorithm:
                startActivity(new Intent(MainActivity.this, AlgorithmActivity.class));
                break;
            case R.id.dagger:
                startActivity(new Intent(MainActivity.this, InjectActivity.class));
                break;
            case R.id.path:
                startActivity(new Intent(MainActivity.this, PathActivity.class));
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
        public String getName(){
            return name;
        }
    }
}

