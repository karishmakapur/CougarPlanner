package com.example.myfirstapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    Button viewSchedule;
    private TextView currentDateTV;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

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

        viewSchedule = v.findViewById(R.id.viewScheduleButton);

        viewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intToDaily = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intToDaily);
            }
        });

        return v;
    }

}
