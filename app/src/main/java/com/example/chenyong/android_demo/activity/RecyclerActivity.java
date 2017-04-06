package com.example.chenyong.android_demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenyong.android_demo.R;
import com.example.chenyong.android_demo.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by focus on 17/4/5.
 */

public class RecyclerActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<Person> mList;
    private static final String KEY_NAME = "key_name";
    private static final String KEY_SEX = "key_sex";
    private boolean change = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setName("name:" + i);
            person.setSex(i % 2 == 0 ? "男" : "女");
            person.setId(i);
            mList.add(person);
        }
        MyAdapter adapter = new MyAdapter(mList);
        adapter.setOnLongClickListener(new MyAdapter.OnItemClick() {
            @Override
            public void onItemLongClick(View view, int position) {
                //在子线程中计算
                Flowable<DiffUtil.DiffResult> flow = Flowable.fromCallable(
                        () -> {
                            List<Person> newData = new ArrayList<Person>();
                            if (change) {
                                for (int i = 0; i < 100; i++) {
                                    Person person = new Person();
                                    if (i % 2 == 0) {
                                        continue;
                                    }
                                    person.setName("name:" + i);
                                    person.setSex(i % 2 == 0 ? "男" : "女");
                                    person.setId(i);
                                    newData.add(person);
                                }
                            } else {
                                for (int i = 0; i < 10; i++) {
                                    Person person = new Person();
                                    person.setName("nameShort:" + i);
                                    person.setSex(i % 2 != 0 ? "男" : "女");
                                    person.setId(i);
                                    newData.add(person);
                                }
                            }
                            change = !change;
                            mList = newData;
                            return DiffUtil.calculateDiff(new MyCallback(adapter.getData(), newData), true);
                        }
                );
                flow.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((diff) ->{
                            diff.dispatchUpdatesTo(adapter);
                            adapter.setData(mList);
                        });
            }

            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
            }
        });
        mRecyclerView.setAdapter(adapter);

    }

    private static class MyCallback extends DiffUtil.Callback {
        private List<Person> mOldData;
        private List<Person> mNewData;
        public MyCallback(List<Person> oldData, List<Person> newData) {
            this.mOldData = oldData;
            this.mNewData = newData;
        }
        @Override
        public int getOldListSize() {
            return mOldData.size();
        }

        @Override
        public int getNewListSize() {
            return mNewData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldData.get(oldItemPosition).getId() == mNewData.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldData.get(oldItemPosition).getName().equals(mNewData.get(newItemPosition).getName());
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            Log.d("position", "getChangePayload: " + oldItemPosition + "=====" + newItemPosition);
            Person oldPerson = mOldData.get(oldItemPosition);
            Person newPerson = mNewData.get(newItemPosition);
            Bundle bundle = new Bundle();
            if (!oldPerson.getName().equals(newPerson.getName())) {
                bundle.putString(KEY_NAME, newPerson.getName());
            }
            if (!oldPerson.getSex().equals(newPerson.getSex())) {
                bundle.putString(KEY_SEX, newPerson.getSex());
            }
            if (bundle.size() == 0) {
                return null;
            }
            return bundle;
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        interface OnItemClick {
            void onItemLongClick(View view, int position);

            void onItemClick(View view, int position);
        }
        private List<Person> mData;
        private OnItemClick mOnItemClick;

        public MyAdapter(List<Person> data) {
            this.mData = data;
        }

        public List<Person> getData() {
            return mData;
        }

        public void setData(List<Person> list) {
            mData = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_text, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Person text = mData.get(position);
            final int finalPosition = position;
            if (mOnItemClick != null) {
                holder.setData(text);
                holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Log.d("view", "onLongClick: " + v.getX() + "====" + v.getY() + "====" + finalPosition);
                        mOnItemClick.onItemLongClick(v, finalPosition);
                        return false;
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads) {
            if (payloads.isEmpty()) {
                onBindViewHolder(holder, position);
            }else {
                Bundle payLoad = (Bundle) payloads.get(0);
                Person text = mData.get(position);
                for (String key : payLoad.keySet()) {
                    switch (key) {
                        case KEY_NAME:
                            holder.mTitle.setText(text.getName());
                            break;
                        case KEY_SEX:
                            holder.mContent.setText(text.getSex());
                            break;
                        default:
                            break;
                    }
                }

            }

            super.onBindViewHolder(holder, position, payloads);
        }

        public void setOnLongClickListener(OnItemClick listener) {
            this.mOnItemClick = listener;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public View mItemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mContent = (TextView) itemView.findViewById(R.id.content);
        }

        public void setData(Person person) {
            mTitle.setText(person.getName());
            mContent.setText(person.getSex());
        }
    }

}

