package com.example.chenyong.android_demo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.chenyong.android_demo.DemoApp;
import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.adapter.NoteAdapter;
import com.example.chenyong.android_demo.dagger.ActivityComponent;
import com.example.chenyong.android_demo.dagger.ActivityModules;
import com.example.chenyong.android_demo.dagger.DaggerActivityComponent;
import com.example.chenyong.android_demo.dagger.SpQualifier;
import com.example.chenyong.android_demo.dao.DaoSession;
import com.example.chenyong.android_demo.dao.Note;
import com.example.chenyong.android_demo.dao.NoteDao;
import com.example.chenyong.android_demo.dao.NoteType;
import com.example.chenyong.android_demo.databinding.ActivityGreenDaoBinding;
import com.example.chenyong.android_demo.guide.AbstractGuide;
import com.example.chenyong.android_demo.guide.MainGuide;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by focus on 17/1/5.
 */

public class GreenDaoActivity extends BaseActivity {
    private ActivityGreenDaoBinding mBinding;
    private long i = 1000;
    private DaoSession daoSession;
    private Query<Note> mNoteQuery;
    private NoteAdapter mNoteAdapter;
    @Inject
    @SpQualifier("share1")
    SharedPreferences mSharedPreferences;
    @Inject
    @SpQualifier("share2")
    SharedPreferences mSharedPreferences2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_green_dao);
        mBinding.setPresenter(new Presenter());
        daoSession = ((DemoApp) getApplication()).getDaoSession();
        mNoteQuery = daoSession.getNoteDao().queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        mNoteAdapter = new NoteAdapter();
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recycler.setAdapter(mNoteAdapter);
        updateView();
        ActivityComponent component = DaggerActivityComponent.builder().applicationComponent(((DemoApp) getApplication()).getApplicationComponent())
                .activityModules(new ActivityModules(this))
                .build();
        component.inject(this);
        Log.d(TAG, "onCreate: mSharedPreferences" + mSharedPreferences);
        Log.d(TAG, "onCreate: mSharedPreferences2" + mSharedPreferences2);
        testLambda();
        AbstractGuide guide = new MainGuide(this);
        LinearLayout linearLayout = new LinearLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(100, 100);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        linearLayout.setLayoutParams(lp);
        linearLayout.setOnClickListener((view) -> Log.d(TAG, "onCreate: foot view on click"));
        guide.addView(linearLayout);
    }

    private void updateView() {
        List<Note> list = mNoteQuery.list();
        mNoteAdapter.setData(list);
    }

    public class Presenter {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_add:
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
                    String comment = simpleDateFormat.format(date);
                    Note note = new Note(Long.valueOf(mBinding.editId.getText().toString()),
                            mBinding.editName.getText().toString(), date, comment, NoteType.TEXT);
                    NoteDao noteDao = daoSession.getNoteDao();
                    noteDao.insert(note);
                    updateView();
                    break;
                case R.id.btn_update:
                    Date date1 = new Date();
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
                    String comment1 = simpleDateFormat1.format(date1);
                    daoSession.getNoteDao().update(new Note(Long.valueOf(mBinding.editId.getText().toString()),
                            "modify", date1, comment1));
                    updateView();
                    break;
            }
        }
    }

    private void testLambda() {
        Observable<String> observable = Observable.create((onSub) -> {
            try {
                Thread.sleep(3000);
                onSub.onNext("123");
                Thread.sleep(3000);
                onSub.onNext("234");
                Thread.sleep(3000);
                onSub.onNext("345");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((onNext) -> Log.d(TAG, "testLambda: " + onNext),
                        (onError) -> Log.d(TAG, "testLambda: " + onError.getMessage())
                        );

    }
}
