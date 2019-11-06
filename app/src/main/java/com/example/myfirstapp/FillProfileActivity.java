package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FillProfileActivity extends AppCompatActivity {

    Button saveButton;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profile);

        //setting up the action bar
        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Create Your Cougar Planner Profile");

        saveButton = findViewById(R.id.saveProfileButton);

        //handle save button
        // TODO: update the users information in the database upon save
        // TODO: add error handling: Make sure all fields are inputted, else ask user to finish filling out form.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FillProfileActivity.this, "You clicked the save button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(FillProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
