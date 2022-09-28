package com.example.olayiwola_olanrewaju_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class fragment_checkout extends Fragment {
Button payBtn;
EditText address,phoneNumber,postalCode,cardName,cardNumber, cardDate,cvv;

    public fragment_checkout() {
        // Required empty public constructor
    }


    public static fragment_checkout newInstance(String param1, String param2) {
        fragment_checkout fragment = new fragment_checkout();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        payBtn = view.findViewById(R.id.payBtn);
        address = view.findViewById(R.id.deliveryAddress);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        postalCode = view.findViewById(R.id.postalCode);
        cardName = view.findViewById(R.id.cardHolderName);
        cardNumber = view.findViewById(R.id.cardNumber);
        cardDate = view.findViewById(R.id.expiryDate);
        cvv = view.findViewById(R.id.cvv);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deliveryAddy = address.getText().toString();
                if(deliveryAddy.isEmpty()) {
                    address.setError("Please enter delivery address");
                    return;
                }
                String phoneNumberValue = phoneNumber.getText().toString();
                if(phoneNumberValue.isEmpty()) {
                    phoneNumber.setError("Please enter phone number");
                    return;
                }
                String postalCodeValue = postalCode.getText().toString();
                if(postalCodeValue.isEmpty()) {
                    postalCode.setError("Please enter a valid postal code");
                    return;
                }
                String cardNameValue = cardName.getText().toString();
                if(cardNameValue.isEmpty()) {
                    cardName.setError("Please enter card holder full name");
                    return;
                }
                String cardNumberValue = cardNumber.getText().toString();
                if(cardNumberValue.isEmpty()) {
                    cardNumber.setError("Please enter credit card number");
                    return;
                }
                String cardDateValue = cardDate.getText().toString();
                if(cardDateValue.isEmpty()) {
                    cardDate.setError("Please enter credit card number");
                    return;
                }
                String cvvValue = cvv.getText().toString();
                if(cvvValue.isEmpty()) {
                    cvv.setError("Please enter credit card number");
                    return;
                }
                getParentFragmentManager().beginTransaction().replace(R.id.frameLayout,new fragment_success()).commit();
            }
        });
        return view;
    }
}