package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    ActionBar actionBar;
    Spinner PrioritySpinner;
    Button cancelButton;
    Button submitTask;
    EditText TaskNameET;
    EditText CourseNameET;
    EditText DueDateET;
    EditText NotesET;


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
        TaskNameET = findViewById(R.id.editTaskName);
        CourseNameET = findViewById(R.id.editTaskCourseName);
        DueDateET = findViewById(R.id.editTaskDueDate);
        NotesET = findViewById(R.id.editNotes);


        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddTaskActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.PriorityLevel));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PrioritySpinner.setAdapter(adapter);

        //handling users request to add a course
        //When user clicks the "Add Task" button, add the task to the users database and connect to course
        //TODO: error handling: if user enters course that doesn't exist: our app creates the course. But it should also tell user they need to create course
        submitTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddTaskActivity.this, "You clicked the submit a task button!", Toast.LENGTH_SHORT).show();

                Task task = new Task();
                task.setTaskName(TaskNameET.getText().toString());
                task.setCourse(CourseNameET.getText().toString());
                task.setDueDate(DueDateET.getText().toString());
                task.setPriorityLevel(PrioritySpinner.getSelectedItem().toString());
                task.setNotes(NotesET.getText().toString());

                new FirebaseDatabaseHelperTask().addTask(task, new FirebaseDatabaseHelperTask.TaskStatus() {
                    @Override
                    public void TaskIsLoaded(List<Task> tasks, List<String> keys) {

                    }

                    @Override
                    public void TaskIsInserted() {


                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });


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
