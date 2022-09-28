package com.example.olayiwola_olanrewaju_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Cart extends Fragment implements CartAdapter.countListener {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<product> cartList;
    Button checkoutBtn;
    TextView total;
    View view;
    public Cart() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_cart, container, false);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);
        total = view.findViewById(R.id.total);
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getParentFragmentManager().setFragmentResultListener("cartList", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                cartList = result.getParcelableArrayList("cart");
                getTotal(cartList);

                cartAdapter = new CartAdapter(view.getContext(),cartList,Cart.this);
                recyclerView.setAdapter(cartAdapter);
                recyclerView.setNestedScrollingEnabled(false);
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartList != null && cartList.size() > 0){
                    getParentFragmentManager().beginTransaction().replace(R.id.frameLayout,new fragment_checkout()).commit();
                }else{
                    Toast.makeText(v.getContext(),"Your Cart is Empty",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void increaseProductCount(String productName) {
        ArrayList<product> newCartList = new ArrayList<>();

        for(int i =0; i<cartList.size();i++){
            product product = cartList.get(i);
            if(product.getName() == productName){
                product.setCount(product.getCount()+1);
            }
            newCartList.add(product);
        }
        cartList = newCartList;
        getTotal( cartList);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void decreaseProductCount(String productName) {
        ArrayList<product> newCartList = new ArrayList<>();

        for(int i =0; i<cartList.size();i++){
            product product = cartList.get(i);
            if(product.getName() == productName && product.getCount()>1){
                product.setCount(product.getCount()-1);
            }
            newCartList.add(product);
        }
        getTotal( newCartList);
        cartList = newCartList;

        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteProduct(String productName) {
        for(int i =0; i<cartList.size();i++){
            product product = cartList.get(i);
            if(product.getName() == productName ){
                cartList.remove(i);
            }
        }
        getTotal( cartList);
        cartAdapter.notifyDataSetChanged();
    }

    public  void getTotal(  ArrayList<product> cartList){
        Float totalPrice =0.00f;
        for(int i = 0; i< cartList.size(); i++){
            totalPrice += cartList.get(i).getPrice() * cartList.get(i).getCount();
        }
        total.setText(totalPrice.toString() +"CAD");
    }
}