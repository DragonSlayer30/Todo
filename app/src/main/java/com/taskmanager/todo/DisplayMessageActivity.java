package com.taskmanager.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileOutputStream;
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
        if (INDEX < -1 || INDEX > MainActivity.all_tasks.size() - 1) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
            return;
        }

        Todo todo = MainActivity.all_tasks.get(INDEX);
        final TextView editText = findViewById(R.id.title);
        editText.setText(todo.getTitle());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        TextView dueDate = findViewById(R.id.dueDate);
        if (todo.getDue() != null) dueDate.setText("Due : " + dt1.format(todo.getDue()));
        else {
            dueDate.setText("");
        }
        TextView checkBox = findViewById(R.id.dueStatusBoolean);
        if (todo.isDone()) {
            checkBox.setText("Completed");
        } else checkBox.setText("Incomplete");
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
        MainActivity.all_tasks.remove(INDEX);
        String fileContents = "[";
        for (Todo task : MainActivity.all_tasks) {
            fileContents = fileContents + task + "\n,";
        }

        fileContents = fileContents.substring(0, fileContents.length() - 1);
        if (fileContents.length() == 0) fileContents = "[";
        fileContents = fileContents + "]";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(MainActivity.TASK_LIST, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }

}
