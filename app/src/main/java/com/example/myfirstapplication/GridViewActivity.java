package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewActivity extends AppCompatActivity {

    GridView gridView;
    int images[] = {R.drawable.logo1,R.drawable.logo2,R.drawable.logo3,R.drawable.logo4,R.drawable.logo5,R.drawable.logo6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.grid_view);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),images);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(), "Image "+(position+1)+" selected", Toast.LENGTH_SHORT).show();
//                String selectedItem = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), selectedItem , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GridViewActivity.this,GridViewItemActivity.class);
                intent.putExtra("images",images[position]);
                startActivity(intent);

            }
        });
    }
}
