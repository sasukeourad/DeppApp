package com.example.ubuntu.depapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.CallLog;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    int i = 0;
    int delay = 1;
    Calendar c;
    int second;
    int hour;
    int minute;
    int ampm;
    private static Timer timer = new Timer();
    String duration="";
    String oldDuration=MainActivity.duration1;
    CursorLoader cursorLoader;
    Cursor managedCursor;
    int durationIndex;
    Intent dialogIntent;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        //while(i<10) {
        Toast.makeText(this, "Service started ", Toast.LENGTH_LONG).show();
        c = Calendar.getInstance();
        second = c.get(Calendar.SECOND);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        ampm = c.get(Calendar.AM_PM);
        //Toast.makeText(this, hour + " " + minute + " " + second + " " + ampm+" "+oldDuration, Toast.LENGTH_LONG).show();
        cursorLoader = new CursorLoader(this,CallLog.Calls.CONTENT_URI, null, null, null, null);
         dialogIntent = new Intent(this, dialog.class);


        timer.scheduleAtFixedRate(new mainTask(), 0, 1000 * 3600);

        return START_STICKY;
    }

    private class mainTask extends TimerTask {
        public void run() {
            toastHandler.sendEmptyMessage(0);
        }

    }

    Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (i > 0) {

                //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                // Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                // r.play();
                 //String x;
                //Toast.makeText(getApplicationContext(), "NO ONE CALLED YOU IN ONE HOUR \n \n \n leeeeel \n"+x+"\n", Toast.LENGTH_SHORT).show();
                managedCursor = cursorLoader.loadInBackground();
                durationIndex = managedCursor.getColumnIndex(CallLog.Calls.DATE);
                managedCursor.moveToFirst();
                duration =managedCursor.getString(durationIndex);

                if(duration.equals(oldDuration))
                {
                    //Toast.makeText(getApplicationContext(), "SAAAAAAAAAAAAAAAME", Toast.LENGTH_SHORT).show();

                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
                }
                else if(!duration.equals(oldDuration))
                {
                    Toast.makeText(getApplicationContext(), "YOU HAVE FRIENDS", Toast.LENGTH_SHORT).show();
                    oldDuration=duration;
                }



            }
            i++;
        }
    };


    public void onDestroy() {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
        timer.cancel();

    }



}
