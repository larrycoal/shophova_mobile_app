package com.example.olayiwola_olanrewaju_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ArrayList<product> cartList;
   countListener countListener;
    public CartAdapter() {
    }

    public CartAdapter(Context context, ArrayList<product> cartList,countListener countListener) {
        this.context = context;
        this.cartList = cartList;
        this.countListener = countListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);

        return new CartAdapter.MyViewHolder(v,countListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        product product = cartList.get(position);
        holder.cartItemName.setText(product.getName());
        holder.cartItemPrice.setText(product.getPrice().toString());
        holder.cartItemCount.setText(product.getCount().toString());
        Glide.with(context).asBitmap().load(product.getImage()).into(holder.cartimage);

        holder.increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.countListener.increaseProductCount(product.getName());

            }
        });
        holder.decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.countListener.decreaseProductCount(product.getName());
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.countListener.deleteProduct(product.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cartimage;
        TextView cartItemName, cartItemPrice, cartItemCount;
        Button increaseBtn,decreaseBtn;
        ImageButton deleteBtn;
        countListener countListener;

        public MyViewHolder(@NonNull View itemView,countListener countListener) {
            super(itemView);
            cartItemCount= itemView.findViewById(R.id.count);
            cartimage = itemView.findViewById(R.id.cartItemImage);
            cartItemName =itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            this.countListener = countListener;
        }


    }
    interface countListener{
        void increaseProductCount(String productName);
        void decreaseProductCount(String productName);
        void deleteProduct(String productName);
    }
}
