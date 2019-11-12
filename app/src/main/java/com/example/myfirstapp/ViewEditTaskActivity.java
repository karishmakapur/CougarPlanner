package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewEditTaskActivity extends AppCompatActivity {

    private String selectedTaskID;
    TextView taskName;
    EditText editCoursename;
    EditText editduedate;
    Spinner changepriority;
    EditText editnotes;
    Button saveTaskButton;
    Button cancelButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid = firebaseAuth.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_task);

        selectedTaskID = getIntent().getExtras().get("taskId").toString();

        Toast.makeText(this, "Task id" + selectedTaskID, Toast.LENGTH_SHORT).show();

        //getting references
        taskName = findViewById(R.id.TaskNameTextView);
        editCoursename = findViewById(R.id.editTaskCourseName);
        editduedate = findViewById(R.id.editTaskDueDate);
        changepriority = findViewById(R.id.prioritySpinner);
        editnotes = findViewById(R.id.editNotes);
        saveTaskButton = findViewById(R.id.saveTaskButton);
        cancelButton = findViewById(R.id.cancelButton);

        //populate data from database to edit text and spinner fields
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("tasks/" + uid + "/" + selectedTaskID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String course = dataSnapshot.child("course").getValue().toString();
                    String duedate = dataSnapshot.child("dueDate").getValue().toString();
                    String notes = dataSnapshot.child("notes").getValue().toString();
                    String prioritylevel = dataSnapshot.child("priorityLevel").getValue().toString();
                    String taskname = dataSnapshot.child("taskName").getValue().toString();

                    editCoursename.setText(course);
                    editduedate.setText(duedate);
                    taskName.setText(taskname);
                    editnotes.setText(notes);


                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ViewEditTaskActivity.this, R.array.PriorityLevel, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    changepriority.setAdapter(adapter);
                    if (prioritylevel != null) {
                        int spinnerPosition = adapter.getPosition(prioritylevel);
                        changepriority.setSelection(spinnerPosition);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //handling users request to add a course
        //When user clicks the "Add Task" button, add the task to the users database and connect to course
        //TODO: error handling: if user enters course that doesn't exist: it should tell user they need to create course
       saveTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ViewEditTaskActivity.this, "You clicked the save a task button!", Toast.LENGTH_SHORT).show();

                Task task = new Task();
                task.setTaskName(taskName.getText().toString());
                task.setCourse(editCoursename.getText().toString());
                task.setDueDate(editduedate.getText().toString());
                task.setPriorityLevel(changepriority.getSelectedItem().toString());
                task.setNotes(editnotes.getText().toString());

                databaseReference.child("priorityLevel").setValue(changepriority.getSelectedItem().toString());
                databaseReference.child("notes").setValue(editnotes.getText().toString());
                databaseReference.child("dueDate").setValue(editduedate.getText().toString());
                databaseReference.child("course").setValue(editCoursename.getText().toString());

                Intent intToHome = new Intent(ViewEditTaskActivity.this, ScheduleActivity.class);
                startActivity(intToHome);
            }
        });

        //handle cancel button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ViewEditTaskActivity.this, "You clicked the cancel button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(ViewEditTaskActivity.this, ScheduleActivity.class);
                startActivity(intToHome);
            }
        });


    }
}
