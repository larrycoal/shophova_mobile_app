package com.example.olayiwola_olanrewaju_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class fragment_success extends Fragment {
  Button returnBtn;


    public fragment_success() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_success newInstance(String param1, String param2) {
        fragment_success fragment = new fragment_success();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_success, container, false);

         returnBtn = view.findViewById(R.id.returnHomeBtn);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home()).commit();
            }
        });
        return view;
    }
}