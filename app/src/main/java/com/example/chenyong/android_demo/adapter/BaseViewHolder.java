package com.example.chenyong.android_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by focus on 17/1/6.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setModel(T model);
}
