package com.example.myfirstapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {


    Button ViewFriendButton;
    Button ViewCourseButton;
    Button searchFriendButton;
    Button addCourseButton;
    Button logoutButton;
    Button editProfileButton;
    TextView usersName;


    ImageButton profilePictureImageButton;
    RecyclerView FriendRecycerView;
    RecyclerView CourseRecyclerView;

    ArrayList<Course> courses = new ArrayList<>();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid = firebaseAuth.getUid();

    private DatabaseReference databaseReferenceUsers
            = FirebaseDatabase.getInstance().getReference("users/" + uid);

    private DatabaseReference databaseReferenceCourses = FirebaseDatabase.getInstance().getReference("courses/" + uid);

    StorageReference storageReference;
    private static int IMAGE_REQUEST;
    static {
        IMAGE_REQUEST = 1;
    }
    private Uri imageUri;
    private StorageTask uploadTask;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //profile picture image button.
        // TODO: allow user to switch image with this button
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        //getting all references
        profilePictureImageButton = view.findViewById(R.id.ProfilePicImageButton);
        FriendRecycerView = view.findViewById(R.id.FriendRecyclerView);
        CourseRecyclerView = view.findViewById(R.id.CourseRecyclerView);
        searchFriendButton = view.findViewById(R.id.addFriendButton);
        addCourseButton = view.findViewById(R.id.AddCourseButton);
        editProfileButton = view.findViewById(R.id.EditProfileButton);
        ViewFriendButton = view.findViewById(R.id.friendButton);
        ViewCourseButton = view.findViewById(R.id.courseButton);
        logoutButton = view.findViewById(R.id.LogoutButton);
        usersName = view.findViewById(R.id.NameTextView);


        FriendRecycerView.setHasFixedSize(true);
        FriendRecycerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CourseRecyclerView.setHasFixedSize(true);
        CourseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //get users name and put on screen
        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
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
        if (CourseRecyclerView.getVisibility() == View.VISIBLE) {
            FriendRecycerView.setVisibility(View.VISIBLE);
            CourseRecyclerView.setVisibility(View.INVISIBLE);
        }

        //handle image button
        profilePictureImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You clicked the profile picture button!", Toast.LENGTH_SHORT).show();
                openImage();
            }
        });

        //handle friend button
        ViewFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the friend button!", Toast.LENGTH_SHORT).show();
                if (CourseRecyclerView.getVisibility() == View.VISIBLE) {
                    CourseRecyclerView.setVisibility(View.INVISIBLE);
                }
                FriendRecycerView.setVisibility(View.VISIBLE);
                }
        });

        //handle course button
        ViewCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "You clicked the course button!", Toast.LENGTH_SHORT).show();
                if (FriendRecycerView.getVisibility() == View.VISIBLE) {
                    FriendRecycerView.setVisibility(View.INVISIBLE);
                }
                CourseRecyclerView.setVisibility(View.VISIBLE);
                displayCourses();
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

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }
    private String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading your photo...");
        pd.show();

        if(imageUri== null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWith(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = (Uri) task.getResult();
                        String mUri = downloadUri.toString();

                        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", mUri);
                        //StorageReference.updateChildren(map);
                        databaseReferenceUsers.updateChildren(map);
                        pd.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed...", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }else {
            Toast.makeText(getContext(), "No image selected...", Toast.LENGTH_SHORT).show();
        }
    }
    //This is the end of Upload image
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Toast.makeText(getContext(), "Begining Upload...", Toast.LENGTH_SHORT).show();
        }else{
            uploadImage();
        }
    }


        private void displayCourses() {
            Toast.makeText(getActivity(), "Finding Your Courses", Toast.LENGTH_LONG).show();
            Query firebaseSearchQuery = databaseReferenceCourses.orderByKey();

            Log.d("TAG", "displayCourses: " + firebaseSearchQuery.toString());


        databaseReferenceCourses.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {

                            //get all of the courses (put it into arraylist)
                            courses.add(d.getValue(Course.class));
                            Log.d("TAG", "onDataChange: " + d.getValue(Course.class).getCourseName());

                        }

                        //printing courses to log for debugging purposes
                        for (int i = 0; i < courses.size(); i++) {
                            Log.d("TAG", "displayCourses (loop): " + courses.get(i).getCourseName());
                        }
                    }
                }//onDataChange

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            FirebaseRecyclerAdapter<Course, CourseViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(Course.class, R.layout.courses_layout, CourseViewHolder.class, firebaseSearchQuery) {

                @Override
                protected void populateViewHolder(CourseViewHolder courseViewHolder, Course course, final int i) {


                    Log.d("TAG", "populateViewHolder: " + courses + " i " + i);
                    courseViewHolder.setDetails(courses.get(i));

                    Log.d("TAG", "populateViewHolder: i " + i);

                    courseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String courseId = getRef(i).getKey();

                            Intent IntToCourse= new Intent(getActivity(), ViewEditCourseActivity.class);
                            IntToCourse.putExtra("courseId", courseId);
                            startActivity(IntToCourse);
                        }
                    });

                }
            };

            CourseRecyclerView.setAdapter(firebaseRecyclerAdapter);
            Log.d("TAG", "displayCourse: setting adapter");

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
