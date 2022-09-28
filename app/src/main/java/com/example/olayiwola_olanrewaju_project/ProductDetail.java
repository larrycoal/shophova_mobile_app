package com.example.olayiwola_olanrewaju_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
   Button cancelButton;
   product product;
   TextView nameText,descText,priceText;
    ArrayList<String> imageViewHolder = new ArrayList<>();
     productDetailAdapter pdAdapter;
     ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        product = getIntent().getParcelableExtra("product");
        cancelButton = findViewById(R.id.canceldetails);
        nameText = findViewById(R.id.productDetailName);
        descText = findViewById(R.id.productDetailDescription);
        priceText = findViewById(R.id.productDetailPrice);
        viewPager2 = findViewById(R.id.viewPager2);
        imageViewHolder.add(product.getImage());
        imageViewHolder.add(product.getImage_two());
        nameText.setText(product.getName());
        descText.setText(product.getDescription());
        priceText.setText(product.getPrice().toString());
        Intent goBackIntent = new Intent(this,HomePage.class);

        pdAdapter = new productDetailAdapter(this,imageViewHolder);
        viewPager2.setAdapter(pdAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goBackIntent);
            }
        });
    }
}