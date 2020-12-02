package com.vinid.myfirstproject.sunshine;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by hungnm24 on 4/20/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private static final int TYPE_TODAY = 1;
    private static final int TYPE_NORMAL = 2;

    private ArrayList<WeatherInfo> data;
    private Callback callback;

    public void updateData(ArrayList<WeatherInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public WeatherAdapter(ArrayList<WeatherInfo> data, Callback callback) {
        this.callback = callback;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TODAY;
        } else {
            return TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutResId;
        if (viewType == TYPE_TODAY) {
            layoutResId = R.layout.today_item;
        } else {
            layoutResId = R.layout.normal_item;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view = holder.getView();
        final WeatherInfo info = data.get(position);

        ImageView ivIcon = view.findViewById(R.id.ivIcon);
        if (getItemViewType(position) == TYPE_TODAY) {
            ivIcon.setImageResource(info.getBigIconResId());
        } else {
            ivIcon.setImageResource(info.getSmallIconResId());
        }

        TextView tvDate = view.findViewById(R.id.tvDate);
        tvDate.setText(info.getDate());

        TextView tvDescription = view.findViewById(R.id.tvDescription);
        tvDescription.setText(info.getDescriptionResId());

        TextView tvMaxTemp = view.findViewById(R.id.tvMaxTemp);
        tvMaxTemp.setText(info.getMaxTemp());

        TextView tvMinTemp = view.findViewById(R.id.tvMinTemp);
        tvMinTemp.setText(info.getMinTemp());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position, info);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        View getView() {
            return view;
        }
    }

    interface Callback {
        void onItemClick(int index, WeatherInfo weatherInfo);
    }
}
