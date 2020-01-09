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

public class MainActivity extends AppCompatActivity {

    private Button btnSignUp;
    Button btnsignin;
    private EditText EtEmail,EtPassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        EtEmail = findViewById(R.id.etEmail);
        EtPassword = findViewById(R.id.etPassword);

        btnSignUp = findViewById(R.id.btnsighUp);
        btnsignin = findViewById(R.id.btnsighIn);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new  Intent(MainActivity.this,SighIn.class);
                startActivity(x);
                finish();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }
        });

    }

    private void signUp() {

        String email,password;
        email = EtEmail.getText().toString().trim();
        password = EtPassword.getText().toString().trim();

        if (email.equals("")){

            Toast.makeText(MainActivity.this, "Email Required", Toast.LENGTH_SHORT).show();
        }

        else if (password.equals("")){
            Toast.makeText(MainActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
        }

        else if (password.length()<6){
            Toast.makeText(MainActivity.this, "Password To Short", Toast.LENGTH_SHORT).show();
        }

        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this,SighIn.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Registered Failure", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
