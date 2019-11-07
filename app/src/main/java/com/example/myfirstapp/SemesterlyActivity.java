package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SemesterlyActivity extends AppCompatActivity {

    Spinner viewScheduleSpinner;
    ActionBar actionBar;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesterly);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Semesterly View");

        //getting references
        viewScheduleSpinner = findViewById(R.id.viewSpinner);
        backButton = findViewById(R.id.backButton);


        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(SemesterlyActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ViewSchedule));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewScheduleSpinner.setAdapter(adapter);

        //handling the spinner events
        viewScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedClass = parent.getItemAtPosition(position).toString();

                if(selectedClass.equals("Daily"))
                {
                    Toast.makeText(SemesterlyActivity.this, "You clicked Daily!", Toast.LENGTH_SHORT).show();
                    Intent intToDaily = new Intent(SemesterlyActivity.this,DailyActivity.class);
                    startActivity(intToDaily);

                }
                else if (selectedClass.equals("Weekly")) {
                    Toast.makeText(SemesterlyActivity.this, "You clicked Weekly!", Toast.LENGTH_SHORT).show();
                    Intent intToWeekly = new Intent(SemesterlyActivity.this,WeeklyActivity.class);
                    startActivity(intToWeekly);
                }
                else if(selectedClass.equals("Monthly")) {
                    Toast.makeText(SemesterlyActivity.this, "You clicked Monthly!", Toast.LENGTH_SHORT).show();
                    Intent intToMonthly = new Intent(SemesterlyActivity.this,MonthlyActivity.class);
                    startActivity(intToMonthly);
                }
                else if(selectedClass.equals("Semesterly")) {
                    Toast.makeText(SemesterlyActivity.this, "You clicked Semesterly!", Toast.LENGTH_SHORT).show();
                    Intent intToSemesterly = new Intent(SemesterlyActivity.this,SemesterlyActivity.class);
                    startActivity(intToSemesterly);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SemesterlyActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(SemesterlyActivity.this,HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
