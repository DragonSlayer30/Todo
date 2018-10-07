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
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Todo;

public class Edit_task extends AppCompatActivity {

    private static int INDEX;
    private static Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        INDEX = Integer.parseInt(intent.getStringExtra(DisplayMessageActivity.TASK_INDEX));
        todo = MainActivity.all_tasks.get(INDEX);
        displayDetails();
    }

    public void displayDetails() {
        final TextView editText = findViewById(R.id.title);
        editText.setText(todo.getTitle());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Button dueDate = findViewById(R.id.dueDate);
        dueDate.setText(dt1.format(todo.getDue()));
        TextView description = findViewById(R.id.description);
        CheckBox checkBox = findViewById(R.id.doneStatus);
        checkBox.setChecked(todo.isDone());
        description.setText(todo.getDescription());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = todo.getTitle();
        intent.putExtra(MainActivity.INDEX, todo.getId() + "");
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_task, menu);
        return true;
    }


    public void save_Task(MenuItem item) {
        final TextView editText = findViewById(R.id.title);
        todo.setTitle(editText.getText().toString());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        Button dueDate = findViewById(R.id.dueDate);
        try {
            todo.setDue(dt1.parse(dueDate.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CheckBox checkBox = findViewById(R.id.doneStatus);
        todo.setDone(checkBox.isChecked());
        TextView description = findViewById(R.id.description);
        todo.setDescription(description.getText().toString());
        MainActivity.all_tasks.set(INDEX, todo);
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

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void cancel_Task() {
        onBackPressed();
    }
}