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

public class DailyActivity extends AppCompatActivity {

    ActionBar actionBar;
    Spinner viewScheduleSpinner;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Daily View");



        //getting reference to spinner
        viewScheduleSpinner = findViewById(R.id.viewSpinner);
        backButton = findViewById(R.id.backButton);

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(DailyActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ViewSchedule));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewScheduleSpinner.setAdapter(adapter);

        //handling the spinner events
        viewScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedClass = parent.getItemAtPosition(position).toString();

                if(selectedClass.equals("Daily"))
                {
                    Toast.makeText(DailyActivity.this, "You clicked Daily!", Toast.LENGTH_SHORT).show();
                    Intent intToDaily = new Intent(DailyActivity.this,DailyActivity.class);
                    startActivity(intToDaily);

                }
                else if (selectedClass.equals("Weekly")) {
                    Toast.makeText(DailyActivity.this, "You clicked Weekly!", Toast.LENGTH_SHORT).show();
                    Intent intToWeekly = new Intent(DailyActivity.this,WeeklyActivity.class);
                    startActivity(intToWeekly);
                }
                else if(selectedClass.equals("Monthly")) {
                    Toast.makeText(DailyActivity.this, "You clicked Monthly!", Toast.LENGTH_SHORT).show();
                    Intent intToMonthly = new Intent(DailyActivity.this,MonthlyActivity.class);
                    startActivity(intToMonthly);
                }
                else if(selectedClass.equals("Semesterly")) {
                    Toast.makeText(DailyActivity.this, "You clicked Semesterly!", Toast.LENGTH_SHORT).show();
                    Intent intToSemesterly = new Intent(DailyActivity.this,SemesterlyActivity.class);
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
                Toast.makeText(DailyActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(DailyActivity.this,HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
