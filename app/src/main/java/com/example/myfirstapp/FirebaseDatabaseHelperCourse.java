package com.example.myfirstapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperCourse {

        private FirebaseDatabase mDatabase;
        private DatabaseReference databaseReference;
        private List<Course> courses = new ArrayList<>();

        public interface CourseStatus {
            void CourseIsLoaded(List<Course> courses, List<String> keys);


            void CourseIsInserted();

            void DataIsUpdated();

            void DataIsDeleted();
        }

        public FirebaseDatabaseHelperCourse() {
            this.mDatabase = FirebaseDatabase.getInstance();
            this.databaseReference = mDatabase.getReference("courses");
        }


        public void addCourse(Course course, final CourseStatus dataStatus){

            String key = databaseReference.push().getKey();
            databaseReference.child(key).setValue(course).addOnSuccessListener(new OnSuccessListener<Void>(){
                @Override
                public void onSuccess(Void aVoid){
                    dataStatus.CourseIsInserted();
                }
            });

        }
    }
