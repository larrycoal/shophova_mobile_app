package com.example.olayiwola_olanrewaju_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
   Context context;
   ArrayList<product> productList;
   cartListener cartListener;
    public HomeAdapter() {
    }

    public HomeAdapter(Context context, ArrayList<product> productList,cartListener cartListener) {
        this.context = context;
        this.productList = productList;
        this.cartListener = cartListener;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product,parent,false);
        return new MyViewHolder(v,cartListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
        product product = productList.get(position);
        holder.name.setText(product.name);
        holder.description.setText(product.description);
        holder.price.setText("CAD"+product.price.toString());
        Glide.with(context).asBitmap().load(product.image).into(holder.image);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productDetailIntent = new Intent(v.getContext(),ProductDetail.class);
                productDetailIntent.putExtra("product",product);
                v.getContext().startActivity(productDetailIntent);
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Added to cart",Toast.LENGTH_LONG).show();
                holder.cartListener.addToCart(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size() ;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,description,price;
        ImageView image;
        ConstraintLayout parentLayout;
        Button addToCart;
        cartListener cartListener;

        public MyViewHolder(@NonNull View itemView, cartListener cartListener) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            description= itemView.findViewById(R.id.productDesc);
            image = itemView.findViewById(R.id.productImage);
            price = itemView.findViewById(R.id.price);
            addToCart = itemView.findViewById(R.id.addToCartBtn);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            this.cartListener = cartListener;
        }
    }
    interface cartListener{
        void addToCart(product product);
    }
}
