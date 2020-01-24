package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class GridViewItemActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_item);
        imageView = findViewById(R.id.grid_image);

        Intent i = getIntent();
        int resource = i.getIntExtra("images",0);
        imageView.setImageResource(resource);
    }
}
