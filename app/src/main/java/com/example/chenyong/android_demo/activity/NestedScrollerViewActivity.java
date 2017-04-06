package com.example.chenyong.android_demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.TextView;

import com.example.chenyong.android_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by focus on 17/2/21.
 */

public class NestedScrollerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NestedScrollView mNestedScrollView;
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class Nested1Holder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        Nested1Holder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }

        void setData(String string) {
            mTextView.setText(string);
        }
    }

    private class Nested1Adapter extends RecyclerView.Adapter<Nested1Holder> {
        private List<String> mStringArrayList;

        Nested1Adapter() {
            mStringArrayList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                mStringArrayList.add("text" + i);
            }
        }
        @Override
        public Nested1Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            return new Nested1Holder(view);
        }

        @Override
        public void onBindViewHolder(Nested1Holder holder, int position) {
             holder.setData(mStringArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return mStringArrayList.size();
        }
    }
}
