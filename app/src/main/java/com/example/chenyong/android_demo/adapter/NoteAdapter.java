package com.example.chenyong.android_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.dao.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 17/1/6.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {
    private List<Note> mList = new ArrayList<>();

    public void setData(List<Note> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        Note note = mList.get(position);
        holder.setModel(note);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
