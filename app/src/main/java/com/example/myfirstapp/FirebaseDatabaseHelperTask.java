package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperTask {

    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private List<Task> tasks = new ArrayList<>();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public interface TaskStatus {
        void TaskIsLoaded(List<Task> tasks, List<String> keys);


        void TaskIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public FirebaseDatabaseHelperTask() {
        this.mDatabase = FirebaseDatabase.getInstance();
    }


    public void addTask(final Task task, final TaskStatus dataStatus){

        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseAuth.getUid();
        this.databaseReference = mDatabase.getReference("tasks/" + uid);
        //final String key = databaseReference.push().getKey();



        Log.d(null, "addTask: task course name: " + task.getCourse());



        databaseReference.child(task.getTaskName()).setValue(task).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                dataStatus.TaskIsInserted();
                Log.d(null, "onSuccess: ");
            }
        });


    }
}