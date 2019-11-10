package com.example.myfirstapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperUser {

    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
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

        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseAuth.getUid();

        if(mFirebaseUser != null)
        {
            databaseReference.child(uid).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>(){
                @Override
                public void onSuccess(Void aVoid){
                    dataStatus.UserIsInserted();
                }
            });
        }


    }
}
