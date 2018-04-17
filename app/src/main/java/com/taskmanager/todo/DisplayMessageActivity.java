package com.taskmanager.todo;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        TextView textView = findViewById(R.id.title);
        textView.setText(message);
        if(todo.isDone()) {
            textView.setTextColor(getColor(R.color.successFinished));
        }
        else  textView.setTextColor(getColor(R.color.unfinsihed));
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        TextView dueDate = findViewById(R.id.dueDate);
        dueDate.setText("Due : " + dt1.format(todo.getDue()));
        TextView description = findViewById(R.id.description);
        description.setText(todo.getDescription());
        //startActivity(intent);
    }
}
