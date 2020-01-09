package com.example.uass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SighIn extends AppCompatActivity {

    private Button btnSignin,btnreg;
    private EditText etEmail,etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_in);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnSignin = findViewById(R.id.btnsighIn);
        btnreg = findViewById(R.id.btnregister);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(SighIn.this, SaveData.class);
                startActivity(x);
                finish();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signin();
            }
        });
    }

    private void signin() {
        String email,password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (email.equals("")){
            Toast.makeText(SighIn.this, "Email is Required", Toast.LENGTH_SHORT).show();
        }

        else if (password.equals("")){
            Toast.makeText(SighIn.this, "Password is Required", Toast.LENGTH_SHORT).show();
        }
        else if (password.length()<6){
            Toast.makeText(SighIn.this, "Password too Short", Toast.LENGTH_SHORT).show();
        }

        else{
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent i = new Intent(SighIn.this,Home.class);
                    startActivity(i);
                    finish();

                }else {
                    Toast.makeText(SighIn.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });}
    }
}
