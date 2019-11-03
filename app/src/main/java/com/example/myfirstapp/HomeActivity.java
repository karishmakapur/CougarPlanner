package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private NavController navController;
    BottomNavigationView bottomNavigationView;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment);

        //Setting the navigation controller to Bottom Nav
        bottomNavigationView = findViewById(R.id.middleNav);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Setting up the action bar
        NavigationUI.setupActionBarWithNavController(this, navController);

        //logout button clicked
        logoutButton = findViewById(R.id.LogoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


    }

    private void signOut() {
        if (FirebaseAuth.getInstance() != null) {
            FirebaseAuth.getInstance().signOut();
            Intent intToLogin = new Intent(HomeActivity.this, LoginActivity.class);
            Toast.makeText(HomeActivity.this,"You are logged out", Toast.LENGTH_SHORT).show();
            startActivity(intToLogin);
        }


    }

}
