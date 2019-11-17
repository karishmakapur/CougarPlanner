package com.example.myfirstapp;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    View mView;


    public TaskViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Task task) {

        Log.d("TAG", "setDetails: " + task);

        TextView taskname = (TextView) mView.findViewById(R.id.TaskNameTextView);
        TextView taskcourse = (TextView) mView.findViewById(R.id.TaskCourseTextView);
        TextView tasknotes = (TextView) mView.findViewById(R.id.TaskNotesTextView);
        TextView taskduedate = (TextView) mView.findViewById(R.id.taskDueTextView);
        TextView taskpriority = (TextView) mView.findViewById(R.id.taskPriorityTextView);


        Log.d("TAG", "populateViewHolder: " + task.getTaskName()
                + task.getCourse() + task.getNotes() + task.getDueDate() + task.getPriorityLevel());


        taskname.setText(task.getTaskName());
        taskcourse.setText(task.getCourse());
        tasknotes.setText(task.getNotes());
        taskduedate.setText(task.getDueDate());
        taskpriority.setText(task.getPriorityLevel());


        Log.d("TAG", "setDetails: " + taskname.getText());




    }


}


