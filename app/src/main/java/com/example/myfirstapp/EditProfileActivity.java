package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    Button cancelButton;
    Button saveButton;
    ActionBar actionBar;
    EditText nameET;
    EditText dobET;
    EditText uniET;
    EditText currYearET;
    EditText gradYearET;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid = firebaseAuth.getUid();


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
        nameET = findViewById(R.id.editName);
        dobET = findViewById(R.id.editDateOfBirth);
        uniET = findViewById(R.id.editUniversity);
        currYearET = findViewById(R.id.editCurrentYear);
        gradYearET = findViewById(R.id.editExpectedGraduationYear);

        //populate data from database to edit text and spinner fields
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/" + uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String dob = dataSnapshot.child("dob").getValue().toString();
                    String currYear = dataSnapshot.child("currYear").getValue().toString();
                    String gradYear = dataSnapshot.child("gradyr").getValue().toString();
                    String uniname = dataSnapshot.child("uniname").getValue().toString();

                    nameET.setText(name);
                    dobET.setText(dob);
                    currYearET.setText(currYear);
                    gradYearET.setText(gradYear);
                    uniET.setText(uniname);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

                User user = new User();
                user.setName(nameET.getText().toString());
                user.setCurrYear(currYearET.getText().toString());
                user.setDob(dobET.getText().toString());
                user.setGradyr(gradYearET.getText().toString());
                user.setUniname(uniET.getText().toString());


                databaseReference.child("name").setValue(nameET.getText().toString());
                databaseReference.child("currYear").setValue(currYearET.getText().toString());
                databaseReference.child("dob").setValue(dobET.getText().toString());
                databaseReference.child("gradyr").setValue(gradYearET.getText().toString());
                databaseReference.child("uniname").setValue(uniET.getText().toString());

                Intent intToHome = new Intent(EditProfileActivity.this, HomeActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
