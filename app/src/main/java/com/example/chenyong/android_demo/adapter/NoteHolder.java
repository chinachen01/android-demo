package com.example.chenyong.android_demo.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.example.chenyong.android_demo.dao.Note;
import com.example.chenyong.android_demo.databinding.ItemNoteBinding;

/**
 * Created by focus on 17/1/6.
 */

public class NoteHolder extends BaseViewHolder<Note>{
    private ItemNoteBinding mBinding;
    public NoteHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void setModel(Note model) {
        mBinding.setModel(model);
        mBinding.executePendingBindings();
    }
}
