package com.example.myfirstapp;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    View mView;


    public CourseViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Course course) {

        Log.d("TAG", "setDetails: " + course);

        TextView coursename = (TextView) mView.findViewById(R.id.CourseNameTextView);
        TextView coursemeeting = (TextView) mView.findViewById(R.id.MeetingDaysTextView);
        TextView courseinstructor = (TextView) mView.findViewById(R.id.InstructorNameTextView);
        TextView coursecolor = (TextView) mView.findViewById(R.id.ColorTextView);


        Log.d("TAG", "populateViewHolder: " + course.getCourseName() + course.getMeetingDays()
                        + course.getInstructor() + course.getColor());


        coursename.setText(course.getCourseName());
        coursemeeting.setText(course.getMeetingDays());
        courseinstructor.setText(course.getInstructor());
        coursecolor.setText(course.getColor());

        Log.d("TAG", "setDetails: " + coursename.getText());




    }


}

