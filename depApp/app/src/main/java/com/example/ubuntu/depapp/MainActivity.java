package com.example.ubuntu.depapp;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends ActionBarActivity {
//Context context;
 //int duration ;
   CursorLoader cursorLoader1;
    Cursor managedCursor1;
    int durationIndex1;
    static String duration1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         cursorLoader1 = new CursorLoader(this,CallLog.Calls.CONTENT_URI, null, null, null, null);
         managedCursor1 = cursorLoader1.loadInBackground();


         durationIndex1 = managedCursor1.getColumnIndex(CallLog.Calls.DATE);

        managedCursor1.moveToFirst();

         duration1 =managedCursor1.getString(durationIndex1);


        //TextView t = (TextView) findViewById(R.id.textView);
        //t.setText(duration1);
          Button start= (Button) findViewById(R.id.button);
        Button stop= (Button) findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), MyService.class));
            }
        });
         stop.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MyService.class));
                System.exit(0);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
