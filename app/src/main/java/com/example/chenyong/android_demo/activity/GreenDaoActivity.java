package com.example.chenyong.android_demo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.chenyong.android_demo.DemoApp;
import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.adapter.NoteAdapter;
import com.example.chenyong.android_demo.dao.DaoSession;
import com.example.chenyong.android_demo.dao.Note;
import com.example.chenyong.android_demo.dao.NoteDao;
import com.example.chenyong.android_demo.dao.NoteType;
import com.example.chenyong.android_demo.databinding.ActivityGreenDaoBinding;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by focus on 17/1/5.
 */

public class GreenDaoActivity extends AppCompatActivity {
    private ActivityGreenDaoBinding mBinding;
    private long i = 1000;
    private  DaoSession daoSession;
    private Query<Note> mNoteQuery;
    private NoteAdapter mNoteAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_green_dao);
        mBinding.setPresenter(new Presenter());
        daoSession= ((DemoApp)getApplication()).getDaoSession();
        mNoteQuery = daoSession.getNoteDao().queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        mNoteAdapter = new NoteAdapter();
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recycler.setAdapter(mNoteAdapter);
        updateView();
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
}
