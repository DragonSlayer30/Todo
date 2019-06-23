package com.taskmanager.todo;

import android.content.Context;
import android.content.Intent;
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
    private static int INDEX;
    private TextView taskName = null;
    private Button dueDate = null;
    private TextView description = null;
    private Todo task_selected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_);
        Intent intent = getIntent();
        String message = intent.getStringExtra(DashBoard.EXTRA_MESSAGE);
        INDEX = Integer.parseInt(intent.getStringExtra(DashBoard.INDEX));
        if (INDEX > DashBoard.all_tasks.size() - 1) {
            Intent intent1 = new Intent(this, DashBoard.class);
            startActivity(intent1);
            return;
        }
        taskName = findViewById(R.id.title);
        dueDate = findViewById(R.id.dueDate);
        description = findViewById(R.id.description);
        if (message.equals("create_new") || INDEX == -1) return;
        task_selected = DashBoard.all_tasks.get(INDEX);
        taskName.setText(task_selected.getTitle());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        if (task_selected.getDue() != null)
            dueDate.setText("Due : " + dt1.format(task_selected.getDue()));
        else {
            dueDate.setText("");
            dueDate.setVisibility(View.INVISIBLE);
        }
        description.setText(task_selected.getDescription());
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
        Intent intent = getIntent();
        String message = intent.getStringExtra(DashBoard.EXTRA_MESSAGE);
        String desc = description.getText().toString();
        String title = taskName.getText().toString();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Date due = null;
        try {
            due = dt1.parse(dueDate.getText().toString().split(":")[Math.min(1, dueDate.getText().toString().split(":").length)]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Todo todo = new Todo(DashBoard.all_tasks.size(), title, desc, false, due);
        if (message.equals("create_new")) {
            DashBoard.all_tasks.set(INDEX, todo);
            String fileContents = "[";
            int index = 0;
            for (Todo task : DashBoard.all_tasks) {
                task.setId(index);
                fileContents = fileContents + task + "\n,";
                index++;
            }
            fileContents = fileContents.substring(0, fileContents.length() - 1);
            if (fileContents.length() == 0) fileContents = "[";
            fileContents = fileContents + "]";
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(DashBoard.TASK_LIST, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            onBackPressed();
            return;
        }
        DashBoard.all_tasks.add(todo);
        String fileContents = "[";
        for (Todo task : DashBoard.all_tasks) {
            fileContents = fileContents + task + "\n,";
        }

        fileContents = fileContents.substring(0, fileContents.length() - 1);
        fileContents = fileContents + "]";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(DashBoard.TASK_LIST, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

}
