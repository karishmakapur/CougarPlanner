package com.example.myfirstapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    //private Button viewMenuButton;

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

        //getting the reference to the view menu button
       /* viewMenuButton = v.findViewById(R.id.ViewMenu);

        viewMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.calendar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //menu item click handling
        if(id == R.id.daily)
        {
            Toast.makeText(getActivity(), "Daily clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.weekly)
        {
            Toast.makeText(getActivity(), "Weekly clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.monthly)
        {
            Toast.makeText(getActivity(), "Monthly clicked", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.semesterly)
        {
            Toast.makeText(getActivity(), "Semesterly clicked", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
