package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {

    Button backButton;
    Spinner colorSpinner;
    Button submitCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        backButton = findViewById(R.id.backButton);
        colorSpinner = findViewById(R.id.ColorSpinner);
        submitCourse = findViewById(R.id.submitCourseButton);

        //handle back button
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddCourseActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(AddCourseActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddCourseActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colors));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        //handling users request to add a course
        //TODO: When user clicks the "Add course" button, add the course to the users database
        submitCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddCourseActivity.this, "You clicked the submit a course button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(AddCourseActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }




}
