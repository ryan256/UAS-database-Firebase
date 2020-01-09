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

public class UpdateData extends AppCompatActivity {

    EditText ettitle,etdescription;
    Button btnUpdate;
    String title,id,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

    ettitle = findViewById(R.id.etitle);
    etdescription = findViewById(R.id.edescription);

    btnUpdate = findViewById(R.id.btnupdate);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");

        ettitle.setText(title);
        etdescription.setText(description);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data").child(id);
                String utitle,udescription;

                utitle = ettitle.getText().toString();
                udescription = etdescription.getText().toString();

                UserData userData = new UserData(id,utitle,udescription);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateData.this, "Data Update", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateData.this,ReadData.class);
                startActivity(i);
                finish();
            }
        });
    }
}
