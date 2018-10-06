package com.taskmanager.todo;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        todo = MainActivity.all_tasks[INDEX];
        displayDetails();
    }

    public void displayDetails() {
        final TextView editText = findViewById(R.id.title);
        editText.setText(todo.getTitle());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        TextView dueDate = findViewById(R.id.dueDate);
        dueDate.setText("Due : " + dt1.format(todo.getDue()));
        TextView description = findViewById(R.id.description);
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
        TextView dueDate = findViewById(R.id.dueDate);
        todo.setDue(new Date(dueDate.getText().toString()));
        TextView description = findViewById(R.id.description);
        todo.setDescription(description.getText().toString());
        MainActivity.all_tasks[INDEX] = todo;
        onBackPressed();
    }

    public void cancel_Task() {
        onBackPressed();
    }
}