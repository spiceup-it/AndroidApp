package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);



        String[] title = {"Adidas", "Puma", "Wrangler", "Reebox", "Levis","LEE"};
        String[] subtitle = {"Adidas desc", "Puma desc", "Wrangler desc", "Reebox desc", "Levis desc","LEE desc"};
        Integer[] images = {R.drawable.home, R.drawable.notifications,R.drawable.profile, R.drawable.home,R.drawable.notifications,R.drawable.profile};


        List<Product> productList = new ArrayList<>();
        for(int i=0; i < title.length; i++){
            Product product = new Product();
            product.setImage(images[i]);
            product.setTitle(title[i]);
            product.setSubtitle(subtitle[i]);
            productList.add(product);
        }


//        List<String> input = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            input.add("Test" + i);
//        }// define an adapter

        mAdapter = new RecyclerAdaper(productList,this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
