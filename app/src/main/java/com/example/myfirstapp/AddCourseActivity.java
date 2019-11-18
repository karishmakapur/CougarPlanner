package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddCourseActivity extends AppCompatActivity {

    Button cancelButton;
    Spinner colorSpinner;
    Button submitCourse;
    ActionBar actionBar;
    EditText courseNameET;
    EditText meetingDaysET;
    EditText instructorET;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Add a Course");

        //getting references
        cancelButton = findViewById(R.id.cancelButton);
        colorSpinner = findViewById(R.id.ColorSpinner);
        submitCourse = findViewById(R.id.submitCourseButton);
        courseNameET = findViewById(R.id.NameOfCourseTV);
        meetingDaysET = findViewById(R.id.editMeetingDays);
        instructorET = findViewById(R.id.editInstructorName);


        //handle back button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddCourseActivity.this, "You clicked the cancel button!", Toast.LENGTH_SHORT).show();
                //Intent intToHome = new Intent(AddCourseActivity.this, HomeActivity.class);
                //startActivity(intToHome);
                finish();
            }
        });

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddCourseActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colors));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        //handling users request to add a course
        //When user clicks the "Add course" button, add the course to the users database
        submitCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddCourseActivity.this, "You clicked the submit a course button!", Toast.LENGTH_SHORT).show();


                Course course = new Course();
                course.setColor(colorSpinner.getSelectedItem().toString());
                course.setCourseName(courseNameET.getText().toString());
                course.setInstructor(instructorET.getText().toString());
                course.setMeetingDays(meetingDaysET.getText().toString());

                mDatabase = FirebaseDatabase.getInstance();
                String uid = firebaseAuth.getUid();
                databaseReference = mDatabase.getReference("courses/" + uid);
                databaseReference.child(course.getCourseName()).setValue(course);
                finish();
            }
        });
    }




}