package com.example.olayiwola_olanrewaju_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity  {
    Button loginBtn, signupBtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    EditText usernameInput,passwordInput;
    Intent homeIntent;
    ProgressBar progressBar;
    TextView startShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeIntent = new Intent(this,HomePage.class);
        Intent registerIntent = new Intent(this,register.class);

        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        loginBtn = findViewById(R.id.login_btn);
        signupBtn =findViewById(R.id.signup_btn);
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar2);
        startShopping = findViewById(R.id.anon_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerIntent);
            }
        });
        startShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeIntent);
            }
        });
    }

    public void loginUser(){
        String email = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
         if(email.isEmpty()){
             usernameInput.setError("Email is required");
             usernameInput.requestFocus();
             return;
         }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    startActivity(homeIntent);
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this,"login failed, Username/Password incorrect",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}