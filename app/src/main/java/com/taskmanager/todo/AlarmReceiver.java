package com.taskmanager.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("Receiver", "Received");
        Toast.makeText(context, "OnReceive alarm test", Toast.LENGTH_SHORT).show();

        return;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

}
