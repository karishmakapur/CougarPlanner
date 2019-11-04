package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;


public class ProfileFragment extends Fragment {


    Button ViewFriendButton;
    Button ViewCourseButton;
    Button addFriendButton;
    Button addCourseButton;
    Button editProfileButton;

    ImageButton profilePictureImageButton;
    ScrollView FriendScrollView;
    ScrollView CourseScrollView;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //profile picture image button.
        // TODO: allow user to switch image with this button

        //getting all references
        profilePictureImageButton = view.findViewById(R.id.ProfilePicImageButton);
        FriendScrollView = view.findViewById(R.id.FriendScrollView);
        CourseScrollView = view.findViewById(R.id.CourseScrollView);
        addFriendButton = view.findViewById(R.id.addFriendButton);
        addCourseButton = view.findViewById(R.id.AddCourseButton);
        editProfileButton = view.findViewById(R.id.EditProfileButton);
        ViewFriendButton = view.findViewById(R.id.friendButton);
        ViewCourseButton = view.findViewById(R.id.courseButton);

        //handle image button
        profilePictureImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You clicked the profile picture button!", Toast.LENGTH_SHORT).show();
            }
        });

        //handle friend button
        ViewFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the friend button!", Toast.LENGTH_SHORT).show();
                if (CourseScrollView.getVisibility() == View.VISIBLE) {
                    CourseScrollView.setVisibility(View.INVISIBLE);
                }
                FriendScrollView.setVisibility(View.VISIBLE);
                }
        });

        //handle course button
        ViewCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the course button!", Toast.LENGTH_SHORT).show();
                if (FriendScrollView.getVisibility() == View.VISIBLE) {
                    FriendScrollView.setVisibility(View.INVISIBLE);
                }
                CourseScrollView.setVisibility(View.VISIBLE);
            }
        });

        //handle add friend button
        addFriendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the add friend button!", Toast.LENGTH_SHORT).show();
                Intent intToAddFriend = new Intent(getActivity(),AddFriendActivity.class);
                startActivity(intToAddFriend);
            }
        });

        //handle add course button
        addCourseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the add course button!", Toast.LENGTH_SHORT).show();
                Intent intToAddCourse = new Intent(getActivity(),AddCourseActivity.class);
                startActivity(intToAddCourse);
            }
        });

        //handle edit profile button
        editProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the edit profile button!", Toast.LENGTH_SHORT).show();
                Intent intToEditProfile = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intToEditProfile);
            }
        });
        return view;

    }



}
