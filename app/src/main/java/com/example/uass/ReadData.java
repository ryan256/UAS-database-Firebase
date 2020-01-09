package com.example.uass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<UserData> userData;
    private useradapter Useradapter;

    Button btnback;

    DatabaseReference dRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        btnback = findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReadData.this,Home.class);
                startActivity(i);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<UserData>();

    dRef = FirebaseDatabase.getInstance().getReference().child("User Data");
    dRef.addListenerForSingleValueEvent(valueEventListener);
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                UserData uData = dataSnapshot1.getValue(UserData.class);
                userData.add(uData);
            }
            Useradapter = new useradapter(ReadData.this,userData);
            recyclerView.setAdapter(Useradapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
