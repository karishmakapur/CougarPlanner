package com.example.myfirstapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {


    private TextView currentDateTV;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    Spinner viewScheduleSpinner;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);


        //displaying the current date in the text view above the tasks
        currentDateTV = v.findViewById(R.id.textView2);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        currentDateTV.setText(date);

        //getting reference to spinner
        viewScheduleSpinner = v.findViewById(R.id.viewSpinner);

        //populate spinner with correct data
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ViewSchedule));
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       viewScheduleSpinner.setAdapter(adapter);


        //handling the spinner events
        viewScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedClass = parent.getItemAtPosition(position).toString();

                if(selectedClass.equals("Daily"))
                {
                    Toast.makeText(getActivity(), "You clicked Daily!", Toast.LENGTH_SHORT).show();
                    Intent intToDaily = new Intent(getActivity(),DailyActivity.class);
                    startActivity(intToDaily);
                }
                else if (selectedClass.equals("Weekly")) {
                    Toast.makeText(getActivity(), "You clicked Weekly!", Toast.LENGTH_SHORT).show();
                    Intent intToWeekly = new Intent(getActivity(),WeeklyActivity.class);
                    startActivity(intToWeekly);
                }
                else if(selectedClass.equals("Monthly")) {
                    Toast.makeText(getActivity(), "You clicked Monthly!", Toast.LENGTH_SHORT).show();
                    Intent intToMonthly = new Intent(getActivity(),MonthlyActivity.class);
                    startActivity(intToMonthly);
                }
                else if(selectedClass.equals("Semesterly")) {
                    Toast.makeText(getActivity(), "You clicked Semesterly!", Toast.LENGTH_SHORT).show();
                    Intent intToSemesterly = new Intent(getActivity(),SemesterlyActivity.class);
                    startActivity(intToSemesterly);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

}
