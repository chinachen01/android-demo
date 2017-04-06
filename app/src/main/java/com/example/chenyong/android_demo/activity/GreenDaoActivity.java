package com.example.chenyong.android_demo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.chenyong.android_demo.*;
import com.example.chenyong.android_demo.adapter.NoteAdapter;
import com.example.chenyong.android_demo.dagger.*;
import com.example.chenyong.android_demo.dao.*;
import com.example.chenyong.android_demo.databinding.ActivityGreenDaoBinding;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
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
        RxBus.INSTANCE.send(new TapEvent("green dao event"));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        lp.gravity = Gravity.BOTTOM;
        linearLayout.setOnClickListener((view) -> Log.d(TAG, "onCreate: foot view on click"));
        addContentView(linearLayout, lp);
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
                throw new RuntimeException("add");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((onNext) -> Log.d(TAG, "testLambda: " + onNext),
                        (onError) -> Log.d(TAG, "testLambda: " + onError.getMessage())
                );
        Flowable<String> flowable = Flowable.fromCallable(() -> {
            Thread.sleep(3000);
            String str = "123";
            if (str.equals("123")) {
                throw new RuntimeException("error:123");
            }
            return "123123";
        });
        flowable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(this::log,(error) -> Log.d(TAG, "testLambda: " + error.getMessage()));
    }

    private void log(String string) {
        Log.d(TAG, "log: "+ string);
    }
}
