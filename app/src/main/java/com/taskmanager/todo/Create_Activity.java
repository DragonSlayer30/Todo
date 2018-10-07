package com.taskmanager.todo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Todo;

public class Create_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_task, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        switch (item.getItemId()) {
            case R.id.save_task:
                save_Task(item);
                return true;
            case R.id.cancel_task:
                cancel_Task();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cancel_Task() {
        onBackPressed();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void save_Task(MenuItem item) {
        final TextView editText = findViewById(R.id.title);
        String title = editText.getText().toString();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Button dueDate = findViewById(R.id.dueDate);
        Date due = null;
        try {
            due = dt1.parse(dueDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView description = findViewById(R.id.description);
        String desc = description.getText().toString();
        Todo todo = new Todo(MainActivity.all_tasks.size(), title, desc, false, due);
        MainActivity.all_tasks.add(todo);
        String fileContents = "[";
        for (Todo task : MainActivity.all_tasks) {
            fileContents = fileContents + task + "\n,";
        }

        fileContents = fileContents.substring(0, fileContents.length() - 1);
        fileContents = fileContents + "]";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(MainActivity.TASK_LIST, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

}
