package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WeeklyActivity extends AppCompatActivity {

    ActionBar actionBar;
    Spinner viewScheduleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Weekly View");



        //getting reference to spinner
        viewScheduleSpinner = findViewById(R.id.viewSpinner);

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(WeeklyActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ViewSchedule));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewScheduleSpinner.setAdapter(adapter);

        //handling the spinner events
        viewScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedClass = parent.getItemAtPosition(position).toString();

                if(selectedClass.equals("Daily"))
                {
                    Toast.makeText(WeeklyActivity.this, "You clicked Daily!", Toast.LENGTH_SHORT).show();
                    Intent intToDaily = new Intent(WeeklyActivity.this,DailyActivity.class);
                    startActivity(intToDaily);

                }
                else if (selectedClass.equals("Weekly")) {
                    Toast.makeText(WeeklyActivity.this, "You clicked Weekly!", Toast.LENGTH_SHORT).show();
                    Intent intToWeekly = new Intent(WeeklyActivity.this,WeeklyActivity.class);
                    startActivity(intToWeekly);
                }
                else if(selectedClass.equals("Monthly")) {
                    Toast.makeText(WeeklyActivity.this, "You clicked Monthly!", Toast.LENGTH_SHORT).show();
                    Intent intToMonthly = new Intent(WeeklyActivity.this,MonthlyActivity.class);
                    startActivity(intToMonthly);
                }
                else if(selectedClass.equals("Semesterly")) {
                    Toast.makeText(WeeklyActivity.this, "You clicked Semesterly!", Toast.LENGTH_SHORT).show();
                    Intent intToSemesterly = new Intent(WeeklyActivity.this,SemesterlyActivity.class);
                    startActivity(intToSemesterly);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
