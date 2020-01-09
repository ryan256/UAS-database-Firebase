package com.example.uass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    private EditText title,description;
    private Button btnSave,btnRead;


    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        title = findViewById(R.id.etitle);
        description = findViewById(R.id.edescription);

        btnSave = findViewById(R.id.btnsave);
        btnRead = findViewById(R.id.btnread);

        database = FirebaseDatabase.getInstance().getReference().child("User Data");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId,userTitle,userDescription;

                userId = database.push().getKey();
                userTitle = title.getText().toString();
                userDescription = description.getText().toString();

                if (userTitle.equals("")){
                    Toast.makeText(Home.this, "Title Required", Toast.LENGTH_SHORT).show();
                }
                else if (userDescription.equals("")){
                    Toast.makeText(Home.this, "Description Required", Toast.LENGTH_SHORT).show();
                }
                else{
                 UserData userData = new UserData(userId,userTitle,userDescription);
                 database.child(userId).setValue(userData);
                 Toast.makeText(Home.this,"Save Success",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Home.this,ReadData.class);
                startActivity(i);
                finish();
            }
        });
    }
}
