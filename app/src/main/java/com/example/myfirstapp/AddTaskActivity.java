package com.example.myfirstapp;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    ActionBar actionBar;
    Spinner PrioritySpinner;
    Button cancelButton;
    Button submitTask;
    EditText TaskNameET;
    Spinner CourseNameSpinner;
    EditText DueDateET;
    EditText NotesET;
    ArrayList<String> courseNames = new ArrayList<>();


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
        CourseNameSpinner = findViewById(R.id.editTaskCourseName);
        DueDateET = findViewById(R.id.editTaskDueDate);
        NotesET = findViewById(R.id.editNotes);



        //populate priority spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddTaskActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.PriorityLevel));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PrioritySpinner.setAdapter(adapter);


        //populate course spinner with correct data
        final ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(AddTaskActivity.this,android.R.layout.simple_spinner_item, courseNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CourseNameSpinner.setAdapter(adapter2);

        //fill the course array
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getUid();

        DatabaseReference courseRef = FirebaseDatabase.getInstance().getReference("courses/" + uid);

        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()){
                        Course course = d.getValue(Course.class);
                        courseNames.add(course.getCourseName());
                        adapter2.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //When user clicks the "Add Task" button, add the task to the users database and connect to course
        //error handling: ensure a course exists: this is done with the drop down of courses
        submitTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(AddTaskActivity.this, "You clicked the submit a task button!", Toast.LENGTH_SHORT).show();

                Task task = new Task();
                task.setTaskName(TaskNameET.getText().toString());
                task.setCourse(CourseNameSpinner.getSelectedItem().toString());
                task.setDueDate(DueDateET.getText().toString());
                task.setPriorityLevel(PrioritySpinner.getSelectedItem().toString());
                task.setNotes(NotesET.getText().toString());
                task.setCompleted("false");

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
