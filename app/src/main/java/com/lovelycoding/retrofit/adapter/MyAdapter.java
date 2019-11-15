package com.lovelycoding.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lovelycoding.retrofit.pojo.Posts;
import com.lovelycoding.retrofit.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private static final String TAG = "MyAdapter";
    private List<Posts> mList = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context,List<Posts> mList) {
        this.context=context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent,false);
        return new MyAdpaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyAdpaterViewHolder)holder).tvTitle.setText(mList.get(position).getTitle());
        ((MyAdpaterViewHolder)holder).tvDescription.setText(mList.get(position).getBody());
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: "+mList.size());
        return mList.size();
    }
}
