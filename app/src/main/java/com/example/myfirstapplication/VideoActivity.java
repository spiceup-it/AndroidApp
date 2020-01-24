package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class VideoActivity extends AppCompatActivity {

    private Button btn_play;
    private VideoView videoView;

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        btn_play = findViewById(R.id.btn_play);

        mediaController = new MediaController(this);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoPath = "android.resource://com.example.myfirstapplication/"+R.raw.sample;
                Uri uri = Uri.parse(videoPath);
                videoView.setVideoURI(uri);

                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
                videoView.start();
            }
        });


    }
}
