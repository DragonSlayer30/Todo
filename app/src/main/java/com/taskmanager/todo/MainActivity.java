package com.taskmanager.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.Todo;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Main_Activity";
    public static final String TASK_LIST = "TodoList.json";
    public static String INDEX = "INDEX";
    private static Todo test1 = new Todo( 0,"Test 1", "Sample Description 1", false, new Date());
    private static Todo test2 = new Todo(1, "Test 2", "Sample Description 2", true, new Date());
    private static Todo test3 = new Todo(1, "Test 3", "Sample Description 2", false, new Date());
    private static Todo test4 = new Todo(1, "Test 4", "Sample Description 2", true, new Date());
    private static Todo test5 = new Todo(1, "Test 5", "Sample Description 2", false, new Date());
    private static Todo test6 = new Todo(1, "Test 6", "Sample Description 2", true, new Date());
    private static Todo test7 = new Todo(1, "Test 7", "Sample Description 2", false, new Date());
    private static Todo test8 = new Todo(1, "Test 8", "Sample Description 2", true, new Date());
    //public static Todo[] all_tasks = {test1, test2, test3, test4, test5, test6, test7, test8, test1, test2, test3, test4, test5, test6, test7, test8};
    public static List<Todo> all_tasks = new ArrayList<Todo>();
    //public static List<Todo> all_tasks_demo = new ArrayList<>(Arrays.asList(test1, test2, test3, test4, test5, test6, test7, test8));
    public static List<Todo> all_tasks_demo = new ArrayList<>(Arrays.asList(test1, test2));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File directory = getApplicationContext().getFilesDir();
        File file = new File(directory, TASK_LIST);
        //file.delete();
        if(!file.exists()) {
            Log.e("You are ", "Fucking Stupid and Pathetic Loser, Creating a file");
            createTaskFile();
        }
        //else file.delete();
        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                //text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
            Log.e("You are ", "Fucking Stupid and Pathetic Loser, File not found");
        }
        Gson googleJson = new Gson();
        JsonArray tasksFromJson = googleJson.fromJson(text.toString(), JsonArray.class);
        all_tasks.clear();
        for (JsonElement t : tasksFromJson) {
            Gson task = new Gson();
            Todo taskObject = task.fromJson(t.toString(), Todo.class);
            all_tasks.add(taskObject);
        }
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById(R.id.taskList);
        add_tasks_to_view(view, all_tasks);
    }

    @Override
    public void onResume() {
        super.onResume();
        File directory = getApplicationContext().getFilesDir();
        File file = new File(directory, TASK_LIST);
        //file.delete();
        if (!file.exists()) {
            Log.e("You are ", "Fucking Stupid and Pathetic Loser, Creating a file");
            createTaskFile();
        }
        //else file.delete();
        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                //text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
            Log.e("You are ", "Fucking Stupid and Pathetic Loser, File not found");
        }
        Gson googleJson = new Gson();
        JsonArray tasksFromJson = googleJson.fromJson(text.toString(), JsonArray.class);
        all_tasks.clear();
        for (JsonElement t : tasksFromJson) {
            Gson task = new Gson();
            Todo taskObject = task.fromJson(t.toString(), Todo.class);
            all_tasks.add(taskObject);
        }
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById(R.id.taskList);
        add_tasks_to_view(view, all_tasks);

    }


    public void createTaskFile() {
        String fileContents = "[";
        int index = 0;
        for (Todo task : all_tasks_demo) {
            task.setId(index);
            fileContents = fileContents + task + "\n,";
            index++;
        }
        fileContents = fileContents.substring(0, fileContents.length() - 1);
        if (fileContents.length() == 0) fileContents = "[";
        fileContents = fileContents + "]";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(TASK_LIST, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        TextView editText = findViewById(R.id.textView);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_task:
                Intent intent = new Intent(this, Create_Activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(View view, int index) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = all_tasks.get(index).getTitle();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(INDEX, index+"");
        startActivity(intent);
    }

    private void add_tasks_to_view(ViewGroup view, List<Todo> tasks) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.setMargins(15,15,15,15);
        params.weight = 1;
        Context context = this.getApplicationContext();
        int ind = 0;
        int height = 0;
        for (Todo task: tasks) {
            final TextView task_title = new TextView(context);
            task_title.setText(task.getTitle());
            task_title.setTextSize(25);
            task_title.setLayoutParams(params);
            task_title.setBackground(getDrawable(R.drawable.border));
            task_title.setBackgroundColor(getColor(R.color.whiteBackground));
            task_title.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            task_title.setPadding(15,0,0,0);
            if(task.isDone()) {
                task_title.setTextColor(getColor(R.color.successFinished));
                task_title.setPaintFlags(task_title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else  task_title.setTextColor(getColor(R.color.unfinsihed));
            //task_title.setGravity(Gravity.CENTER);
            final int index = ind;
            task_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMessage(view, index);
                }
            });
            height = task_title.getHeight();
            view.addView(task_title);
            ind = ind + 1;
        }

    }
}
