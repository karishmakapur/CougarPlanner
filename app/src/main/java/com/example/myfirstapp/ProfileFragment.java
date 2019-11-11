package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {


    Button ViewFriendButton;
    Button ViewCourseButton;
    Button searchFriendButton;
    Button addCourseButton;
    Button logoutButton;
    Button editProfileButton;
    TextView usersName;


    ImageButton profilePictureImageButton;
    ScrollView FriendScrollView;
    ScrollView CourseScrollView;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
    String uid = firebaseAuth.getUid();
    private DatabaseReference databaseReference
            = FirebaseDatabase.getInstance().getReference("users/" + uid);


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
        searchFriendButton = view.findViewById(R.id.addFriendButton);
        addCourseButton = view.findViewById(R.id.AddCourseButton);
        editProfileButton = view.findViewById(R.id.EditProfileButton);
        ViewFriendButton = view.findViewById(R.id.friendButton);
        ViewCourseButton = view.findViewById(R.id.courseButton);
        logoutButton = view.findViewById(R.id.LogoutButton);
        usersName = view.findViewById(R.id.NameTextView);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();

                    usersName.setText(name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        //setting default to show the list of friends
        if (CourseScrollView.getVisibility() == View.VISIBLE) {
            FriendScrollView.setVisibility(View.VISIBLE);
            CourseScrollView.setVisibility(View.INVISIBLE);
        }

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

        //handle search / add friend button
        searchFriendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the search/add friend button!", Toast.LENGTH_SHORT).show();
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

        //handle logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return view;

    }


    //signout function called from logout button
    private void signOut() {
        if (FirebaseAuth.getInstance() != null) {
            FirebaseAuth.getInstance().signOut();
            Intent intToLogin = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getActivity(),"You are logged out", Toast.LENGTH_SHORT).show();
            startActivity(intToLogin);
        }


    }



}
