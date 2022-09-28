package com.example.olayiwola_olanrewaju_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.remote.WatchChange;

import java.util.ArrayList;

public class Home extends Fragment implements HomeAdapter.cartListener {
    RecyclerView recyclerView;
    ArrayList<product> productList = new ArrayList<>();
    ArrayList<product> cartList = new ArrayList<>();
    HomeAdapter homeAdapter;
    FirebaseFirestore db;
    Bundle bundle = new Bundle();


    public Home(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        db = FirebaseFirestore.getInstance();
        homeAdapter = new HomeAdapter(view.getContext(),productList,this);

        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(false);


        EventChangeListener();
        return view;

    }

    private void EventChangeListener() {

        db.collection("Products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.e("fetch error",error.getMessage());
                    //Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }
                for (DocumentChange dc :value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                     productList.add(dc.getDocument().toObject(product.class));
                    }
                    homeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void addToCart(product product) {
        cartList.add(product);
        bundle.putParcelableArrayList("cart",(ArrayList<? extends Parcelable>) cartList );
        getParentFragmentManager().setFragmentResult("cartList",bundle);
    }

}