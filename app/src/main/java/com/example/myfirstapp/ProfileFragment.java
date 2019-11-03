package com.example.myfirstapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileFragment extends Fragment {


    private NavController navController;
    BottomNavigationView bottomNavigationView;
    ImageButton profilePictureImageButton;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //profile picture image button.
        // TODO: allow user to switch image with this button
        profilePictureImageButton = view.findViewById(R.id.ProfilePicImageButton);

        profilePictureImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You clicked the button!", Toast.LENGTH_LONG).show();

            }
        });

        //Getting the Navigation Controller
        navController = Navigation.findNavController(getActivity(), R.id.fragment);

        //Setting the navigation controller to Bottom Nav
        //bottomNavigationView = view.findViewById(R.id.middleNav);
        //NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Setting up the action bar
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getActivity(), navController);
        return view;

    }


}
