package com.taskmanager.todo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import model.Todo;

public class DisplayMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Display_activity";
    public static String TASK_INDEX = "";
    private static  int INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        INDEX = Integer.parseInt(intent.getStringExtra(MainActivity.INDEX));
        Todo todo = MainActivity.all_tasks[INDEX];
        final TextView editText = findViewById(R.id.title);
        editText.setText(message);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        TextView dueDate = findViewById(R.id.dueDate);
        dueDate.setText("Due : " + dt1.format(todo.getDue()));
        TextView description = findViewById(R.id.description);
        description.setText(todo.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task_edit:
                edit_Task(item);
                return true;
            case R.id.delete_edit:
                delete_Task(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean  edit_Task(MenuItem item) {
        Intent intent = new Intent(this, Edit_task.class);
        intent.putExtra(EXTRA_MESSAGE, "DISPLAY_ACTIVITY");
        intent.putExtra(TASK_INDEX, INDEX+"");
        startActivity(intent);
        return true;
    }

    public boolean delete_Task(MenuItem item) {
        Log.e("Deleting ", "on Click working");
        return true;
    }

}
