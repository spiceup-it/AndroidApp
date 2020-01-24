package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Services_Music_Player extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services__music__player);
        buttonStart = findViewById(R.id.btn_start);
        buttonStop = findViewById(R.id.btn_stop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
              //  startService(new Intent(this, MyService.class));
                Intent intent1 = new Intent(this, MyService.class);
                startService(intent1);
                break;
            case R.id.btn_stop:
               // stopService(new Intent(this, MyService.class));
                Intent intent2 = new Intent(this, MyService.class);
                stopService(intent2);
                break;
        }
    }
}
