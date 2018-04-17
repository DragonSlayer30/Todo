package com.taskmanager.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.Date;
import java.util.logging.Logger;

import model.Todo;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Main_Activity";
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
    public static Todo[] all_tasks = {test1, test2, test3, test4, test5, test6, test7, test8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById(R.id.taskList);
        add_tasks_to_view(view, all_tasks);
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
    public void sendMessage(View view, int index) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = all_tasks[index].getTitle();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(INDEX, index+"");
        startActivity(intent);
    }

    private void add_tasks_to_view(ViewGroup view, Todo[] tasks) {
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
