package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment);

        //Setting the navigation controller to Bottom Nav
        bottomNavigationView = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Setting up the action bar
        NavigationUI.setupActionBarWithNavController(this, navController);


    }

    //Setting Up the back button
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}
