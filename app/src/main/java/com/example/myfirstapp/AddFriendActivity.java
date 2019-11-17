package com.example.myfirstapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AddFriendActivity extends AppCompatActivity {

    Button backButton;
    Button searchBttn;
    EditText searchET;
    ActionBar actionBar;

    private RecyclerView recyclerView;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //setting up action bar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        actionBar.setTitle("Add a Friend");

        //getting references
        backButton = findViewById(R.id.backButton);
        searchBttn = findViewById(R.id.searchButton);
        searchET = findViewById(R.id.searchField);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");

        recyclerView = (RecyclerView) findViewById(R.id.FriendRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        //handle back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddFriendActivity.this, "You clicked the back button!", Toast.LENGTH_SHORT).show();
                //Intent intToHome = new Intent(AddFriendActivity.this, HomeActivity.class);
                //startActivity(intToHome);
                finish();
            }
        });

        //handle search button
        searchBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(AddFriendActivity.this, "You clicked the search button!", Toast.LENGTH_SHORT).show();

                String searchText = searchET.getText().toString();

                firebaseUserSearch(searchText);

            }
        });


    }

    //connect search bar to database for querying when a user wants to search for a friend
    //When user searches for user, and if user exists, then display user
    // TODO: display user with the option to send friend request

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(AddFriendActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");



        FirebaseRecyclerAdapter<User, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(

                User.class,
                R.layout.searchuser_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {

            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, User user, int i) {

                Log.d("TAG", "populateViewHolder: " + user.getName());

                viewHolder.setDetails(user.getName(), user.getEmail(), user.getUniname());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    //View holder class
    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(String username, String useremail, String useruni){

            Log.d("TAG", "setDetails: " + username + useremail + useruni);
            TextView userName = (TextView) mView.findViewById(R.id.NameTextView);
            TextView userEmail = (TextView) mView.findViewById(R.id.emailTextView);
            TextView userUni = (TextView) mView.findViewById(R.id.universityTextView);


            userName.setText(username);
            userEmail.setText(useremail);
            userUni.setText(useruni);

            Log.d("TAG", "setDetails: " + userName.getText().toString() + userEmail.getText().toString() + userUni.getText().toString());



        }




    }
}
