package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    Button backButton;
    TextView dateTV;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //getting the date from the clicked date from previous activity
        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Tasks Due on " + date);

        //getting references
        backButton = findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ViewActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                Intent intToDaily = new Intent(ViewActivity.this, ScheduleActivity.class);
                startActivity(intToDaily);
            }
        });

    }
}
