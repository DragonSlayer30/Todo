package com.taskmanager.todo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import model.Todo;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int index = Integer.parseInt(intent.getStringExtra(MainActivity.INDEX));
        Todo todo = MainActivity.all_tasks[index];
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

    public void changeTtile() {

    }
}
