package com.example.olayiwola_olanrewaju_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
   EditText fullNameInput, usernameInput,passwordInput,confirmPasswordInput;
   ProgressBar progressBar;
   private FirebaseAuth mAuth;
   private FirebaseDatabase mDatabase;
   Button registerBtn;
   Intent loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
         loginIntent = new Intent(this,MainActivity.class);
        fullNameInput = findViewById(R.id.full_name);
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        confirmPasswordInput = findViewById(R.id.confirm_password);
        registerBtn = findViewById(R.id.register_btn);
        progressBar = findViewById(R.id.progressBar);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerUser();
            }
        });




    }
    public void registerUser(){
        String fullName = fullNameInput.getText().toString().trim();
        String email = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword =confirmPasswordInput.getText().toString().trim();

        if(fullName.isEmpty()){
            fullNameInput.setError("Full name is required");
            fullNameInput.requestFocus();
            return;
        }else if(email.isEmpty()){
           usernameInput.setError("Email is required");
           usernameInput.requestFocus();
           return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            usernameInput.setError("Please enter a valid email address");
            usernameInput.requestFocus();
            return;
        }else if(password.isEmpty()){
            passwordInput.setError("Password is required");
            passwordInput.requestFocus();
            return;
        }else if(!password.equals(confirmPassword)){
            confirmPasswordInput.setError("Password do not match");
            confirmPasswordInput.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email,fullName,password);
                            mDatabase.getReference("users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(register.this,"Registration successful",Toast.LENGTH_LONG).show();
                                                startActivity(loginIntent);
                                            }else{
                                                Toast.makeText(register.this,"Registration unsuccessful",Toast.LENGTH_LONG).show();

                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });

                        }else{
                            Toast.makeText(register.this,"Registration unsuccessful",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }
}