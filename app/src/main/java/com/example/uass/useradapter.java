package com.example.uass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class useradapter extends RecyclerView.Adapter<useradapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserData> userData;

    public useradapter(Context c,ArrayList<UserData>userData){
        this.context = c;
        this.userData = userData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final UserData userData = this.userData.get(position);
        holder.tvTitle.setText(userData.getTitle());
        holder.tvdescription.setText(userData.getDescription());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data").child(userData.getId());
                databaseReference.removeValue();
                Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,UpdateData.class);

                i.putExtra("id",userData.getId());
                i.putExtra("title",userData.getTitle());
                i.putExtra("description",userData.getDescription());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvdescription;
        Button btnDelete,btnUpdate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvdescription = itemView.findViewById(R.id.tvdescription);
            btnDelete = itemView.findViewById(R.id.btndelete);
            btnUpdate = itemView.findViewById(R.id.btnupdate);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }
}
