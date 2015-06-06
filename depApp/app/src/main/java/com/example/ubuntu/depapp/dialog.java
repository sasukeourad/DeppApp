package com.example.ubuntu.depapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ubuntu on 04/06/15.
 */
public class dialog extends Activity {
    Vibrator v;
    //private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.dialog);
        v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        //milliseconds
        v.vibrate(2000);

        Resources res = getResources();
        String[] a = res.getStringArray(R.array.words);

        Random ran = new Random();
        int range= (a.length) - 0 + 1;
        int x = ran.nextInt(range);

        //Toast.makeText(getApplicationContext(), x+" "+a[0]+" "+a[1]+" "+a[2]+" "+a[3], Toast.LENGTH_SHORT).show();
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("HEY Lonely...");
        alertDialog.setIcon(R.drawable.small);
        alertDialog.setMessage(a[x]);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                v.cancel();
                finish();
            }
        });
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
        //finish();
    }
}
