package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FillProfileActivity extends AppCompatActivity {

    Button saveButton;
    ActionBar actionBar;
    EditText nameET;
    EditText dobET;
    EditText UniET;
    EditText GradYearET;
    EditText EmailET;
    Spinner CurrentYearSpinner;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


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
        dobET = findViewById(R.id.editDateOfBirth);
        UniET = findViewById(R.id.editUniversity);
        GradYearET = findViewById(R.id.editExpectedGraduationDate);
        EmailET = findViewById(R.id.editEmail);
        CurrentYearSpinner = findViewById(R.id.CurrentYearSpinner);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(FillProfileActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.CurrentYear));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrentYearSpinner.setAdapter(adapter);



        //handle save button
        // update the users information in the database upon save
        // TODO: add error handling: Make sure all fields are inputted, else ask user to finish filling out form.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FillProfileActivity.this, "You clicked the save button!", Toast.LENGTH_SHORT).show();


                User user = new User();
                user.setName(nameET.getText().toString());
                user.setDob(dobET.getText().toString());
                user.setEmail(EmailET.getText().toString());
                user.setGradyr(GradYearET.getText().toString());
                user.setCurrYear(CurrentYearSpinner.getSelectedItem().toString());
                user.setUniname(UniET.getText().toString());
                user.setImageURL("default");


                mDatabase = FirebaseDatabase.getInstance();
                databaseReference = mDatabase.getReference("users");
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                String uid = firebaseAuth.getUid();

                if(mFirebaseUser != null)
                {
                    databaseReference.child(uid).setValue(user);
                }



                Intent intToHome = new Intent(FillProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}