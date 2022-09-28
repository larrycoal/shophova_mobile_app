package com.example.olayiwola_olanrewaju_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class productDetailAdapter extends RecyclerView.Adapter<productDetailAdapter.ViewHolder> {
    ArrayList<String> imageList;
    Context context;


    public productDetailAdapter(Context context,ArrayList<String> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    public productDetailAdapter() {
    }

    @NonNull
    @Override
    public productDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull productDetailAdapter.ViewHolder holder, int position) {
        String images = imageList.get(position);
        Glide.with(context).asBitmap().load(images).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productDetailImg);
        }
    }
}
