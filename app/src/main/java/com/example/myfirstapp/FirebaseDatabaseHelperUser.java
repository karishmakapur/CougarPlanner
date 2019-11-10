package com.example.myfirstapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperUser {

    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private List<User> users = new ArrayList<>();

    public interface UserStatus{
        void UserIsLoaded(List<User> users, List<String> keys);
        void UserIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelperUser() {
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("users");

    }


    public void addUser(User user, final UserStatus dataStatus){

        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                dataStatus.UserIsInserted();
            }
        });

    }
}
