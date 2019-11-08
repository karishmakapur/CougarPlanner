package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    ActionBar actionBar;
    Spinner PrioritySpinner;
    Button cancelButton;
    Button submitTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Add Task");

        //getting references
        cancelButton = findViewById(R.id.cancelButton);
        PrioritySpinner = findViewById(R.id.prioritySpinner);
        submitTask = findViewById(R.id.addTaskButton);

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddTaskActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.PriorityLevel));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PrioritySpinner.setAdapter(adapter);

        //handling users request to add a course
        //TODO: When user clicks the "Add Task" button, add the task to the users database and connect to course
        submitTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddTaskActivity.this, "You clicked the submit a course button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(AddTaskActivity.this, ScheduleActivity.class);
                startActivity(intToHome);
            }
        });

        //handle cancel button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddTaskActivity.this, "You clicked the cancel button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(AddTaskActivity.this, ScheduleActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
