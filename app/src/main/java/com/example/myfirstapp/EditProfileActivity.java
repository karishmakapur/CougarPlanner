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

public class EditProfileActivity extends AppCompatActivity {

    Button cancelButton;
    Button saveButton;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        //setting up the action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Edit Profile");

        //getting references
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveProfileButton);

        //handle back button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(EditProfileActivity.this, "You clicked the cancel button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(EditProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });

        //handle save button
        // TODO: update the users information in the database upon save
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(EditProfileActivity.this, "You clicked the save button!", Toast.LENGTH_SHORT).show();
                Intent intToHome = new Intent(EditProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
