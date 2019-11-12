package com.example.myfirstapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    Button viewSchedule;
    private TextView currentDateTV;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid = firebaseAuth.getUid();
    ArrayList<Task> tasks = new ArrayList<>();


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //displaying the current date in the text view above the tasks
        currentDateTV = v.findViewById(R.id.textView2);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        currentDateTV.setText("Tasks Due " + date);

        //getting references
        viewSchedule = v.findViewById(R.id.viewScheduleButton);
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("tasks/" + uid);

        recyclerView = (RecyclerView) v.findViewById(R.id.taskRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        displayTasks(date);

        //handle view schedule button
        viewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToDaily = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intToDaily);
            }
        });

        return v;
    }


    private void displayTasks(final String day) {
        Toast.makeText(getActivity(), "Finding Today's Tasks", Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery = databaseReference.orderByKey();

        Log.d("TAG", "displayTasks: " + firebaseSearchQuery.toString());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        //get all of the tasks (put it into arraylist) if their due date is the current date.
                        //if (d.getValue(Task.class).getDueDate().equals(day)) {
                        tasks.add(d.getValue(Task.class));
                        Log.d("TAG", "onDataChange: " + d.getValue(Task.class).getTaskName());
                        //}

                    }

                    //printing tasks to log for debugging purposes
                    for (int i = 0; i < tasks.size(); i++) {
                        Log.d("TAG", "displayTasks (loop): " + tasks.get(i).getTaskName());
                    }
                }
            }//onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseRecyclerAdapter<Task, TaskViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(Task.class, R.layout.tasks_layout, TaskViewHolder.class, firebaseSearchQuery) {

            @Override
            protected void populateViewHolder(TaskViewHolder taskViewHolder, Task task, final int i) {

                if(!task.getDueDate().equals(day)){
                    taskViewHolder.itemView.setVisibility(View.GONE);
                }

                Log.d("TAG", "populateViewHolder: " + tasks + " i " + i);
                taskViewHolder.setDetails(tasks.get(i));

                Log.d("TAG", "populateViewHolder: i " + i);

                taskViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskId = getRef(i).getKey();

                        Intent IntToTask = new Intent(getActivity(), ViewEditTaskActivity.class);
                        IntToTask.putExtra("taskId", taskId);
                        startActivity(IntToTask);
                    }
                });



            }
        };


        recyclerView.setAdapter(firebaseRecyclerAdapter);
        Log.d("TAG", "displayTasks: setting adapter");


    }


}
