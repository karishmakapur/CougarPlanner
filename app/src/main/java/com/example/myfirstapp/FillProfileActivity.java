package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class FillProfileActivity extends AppCompatActivity {

    Button saveButton;
    ActionBar actionBar;
    EditText nameET;
    EditText passwordET;
    EditText dobET;
    EditText UniET;
    EditText CurYearET;
    EditText GradYearET;
    EditText EmailET;


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

        //creating references
        saveButton = findViewById(R.id.saveProfileButton);
        nameET = findViewById(R.id.editName);
        passwordET = findViewById(R.id.editPassword);
        dobET = findViewById(R.id.editDateOfBirth);
        UniET = findViewById(R.id.editUniversity);
        CurYearET = findViewById(R.id.editCurrentYear);
        GradYearET = findViewById(R.id.editExpectedGraduationDate);
        EmailET = findViewById(R.id.editEmail);


        //handle save button
        // TODO: update the users information in the database upon save
        // TODO: add error handling: Make sure all fields are inputted, else ask user to finish filling out form.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(FillProfileActivity.this, "You clicked the save button!", Toast.LENGTH_SHORT).show();

                User user = new User();
                user.setName(nameET.getText().toString());
                user.setDob(dobET.getText().toString());
                user.setEmail(EmailET.getText().toString());
                user.setGradyr(GradYearET.getText().toString());
                user.setCurrYear(CurYearET.getText().toString());
                user.setUniname(UniET.getText().toString());
                user.setPassword(passwordET.getText().toString());


                new FirebaseDatabaseHelperUser().addUser(user, new FirebaseDatabaseHelperUser.UserStatus() {
                    @Override
                    public void UserIsLoaded(List<User> users, List<String> keys) {

                    }


                    @Override
                    public void UserIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });


                Intent intToHome = new Intent(FillProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
