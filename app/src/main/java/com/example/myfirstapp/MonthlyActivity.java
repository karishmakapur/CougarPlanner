package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MonthlyActivity extends AppCompatActivity {

    Spinner viewScheduleSpinner;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Monthly View");

        //getting references
        viewScheduleSpinner = findViewById(R.id.viewSpinner);

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(MonthlyActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ViewSchedule));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewScheduleSpinner.setAdapter(adapter);

        //handling the spinner events
        viewScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedClass = parent.getItemAtPosition(position).toString();

                if(selectedClass.equals("Daily"))
                {
                    Toast.makeText(MonthlyActivity.this, "You clicked Daily!", Toast.LENGTH_SHORT).show();
                    Intent intToDaily = new Intent(MonthlyActivity.this,DailyActivity.class);
                    startActivity(intToDaily);

                }
                else if (selectedClass.equals("Weekly")) {
                    Toast.makeText(MonthlyActivity.this, "You clicked Weekly!", Toast.LENGTH_SHORT).show();
                    Intent intToWeekly = new Intent(MonthlyActivity.this,WeeklyActivity.class);
                    startActivity(intToWeekly);
                }
                else if(selectedClass.equals("Monthly")) {
                    Toast.makeText(MonthlyActivity.this, "You clicked Monthly!", Toast.LENGTH_SHORT).show();
                    Intent intToMonthly = new Intent(MonthlyActivity.this,MonthlyActivity.class);
                    startActivity(intToMonthly);
                }
                else if(selectedClass.equals("Semesterly")) {
                    Toast.makeText(MonthlyActivity.this, "You clicked Semesterly!", Toast.LENGTH_SHORT).show();
                    Intent intToSemesterly = new Intent(MonthlyActivity.this,SemesterlyActivity.class);
                    startActivity(intToSemesterly);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
