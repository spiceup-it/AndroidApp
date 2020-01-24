package com.example.myfirstapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class MyAlarm extends BroadcastReceiver {

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyAlarm", "Alarm just fired");
        mp = MediaPlayer.create(context, R.raw.music);
        mp.start();

        // Vibrate the mobile phone
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(5000);

        Toast.makeText(context, "ALARM...", Toast.LENGTH_SHORT).show();
    }
}
