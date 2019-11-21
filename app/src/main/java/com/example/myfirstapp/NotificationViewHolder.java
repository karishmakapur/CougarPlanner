package com.example.myfirstapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    private View mView;
    private String name;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(FriendRequests friendRequests, Context context) {

        Log.d("TAG", "setDetails: " + friendRequests.getSenderID());

        final TextView sender = (TextView) mView.findViewById(R.id.nameOfUserTV);

        //TODO: query database to get name using id;

        Query query = FirebaseDatabase.getInstance().getReference("users/" + friendRequests.getSenderID() + "/name");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TAG", "onDataChange: " + dataSnapshot.getValue().toString());
                name = dataSnapshot.getValue().toString();
                sender.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("TAG", "populateViewHolder: " + friendRequests.getSenderID());



        sender.setText(name);

        Log.d("TAG", "setDetails: " + sender.getText());




    }
}
