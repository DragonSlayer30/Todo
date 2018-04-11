package com.taskmanager.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.Date;
import java.util.logging.Logger;

import model.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LinearLayout view = findViewById(R.id.tasks);
        //add_tasks_to_view(view, all_tasks);
    }

    private Todo test1 = new Todo("Test 1", "Sample Description 1", false, new Date());
    private Todo test2 = new Todo("Test 2", "Sample Description 2", true, new Date());
    private Todo[] all_tasks = {test1, test2};

    private void add_tasks_to_view(ViewGroup view, Todo[] tasks) {

        /*
        ViewGroup current_view = this.findViewById(R.id.tasks);
        Context context = this.getApplicationContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(R.id.tas);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (Todo task: tasks) {
            Log.d("Task", task.getTitle());
            TextView task_title = new TextView(context);
            task_title.setText(task.getTitle());
            task_title.setLayoutParams(params);
            linearLayout.addView(task_title);
        }
        Log.d("Length", "" + linearLayout.getChildCount());
        View.inflate(context, linearLayout.getId(), current_view);
        current_view.addView(linearLayout);
        */

    }
}
