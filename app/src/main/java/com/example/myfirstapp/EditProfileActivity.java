package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    Button backButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        backButton = findViewById(R.id.backButton);
        saveButton = findViewById(R.id.saveProfileButton);

        //handle back button
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(EditProfileActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
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
