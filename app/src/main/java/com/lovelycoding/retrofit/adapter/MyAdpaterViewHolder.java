package com.lovelycoding.retrofit.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lovelycoding.retrofit.R;

public class MyAdpaterViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle,tvDescription;
    public MyAdpaterViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle=itemView.findViewById(R.id.title);
        tvDescription=itemView.findViewById(R.id.description);

    }
}
