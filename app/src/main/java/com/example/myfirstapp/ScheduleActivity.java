package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class ScheduleActivity extends AppCompatActivity {

    ActionBar actionBar;
    Button backButton;
    CalendarView calendarView;
    Button addTaskButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Schedule");

        //getting references
        backButton = findViewById(R.id.backButton);
        addTaskButton = findViewById(R.id.addTaskButton);
        calendarView = findViewById(R.id.calendarView);



        //handle back button
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ScheduleActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(ScheduleActivity.this,HomeActivity.class);
                startActivity(intToHome);
            }
        });

        //handle add task button
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ScheduleActivity.this, "You clicked the add task button!", Toast.LENGTH_SHORT).show();
                Intent intToTask = new Intent(ScheduleActivity.this,AddTaskActivity.class);
                startActivity(intToTask);
            }
        });

        //getting the calendar set up
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Intent intent = new Intent(ScheduleActivity.this, ViewActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

    }
}