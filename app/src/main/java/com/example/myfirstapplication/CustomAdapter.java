package com.example.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int images[];
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, int[] images) {
        this.context = applicationContext;
        this.images=images;
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_gridview_layout,null);
        ImageView imageView = view.findViewById(R.id.grid_view_item);
        imageView.setImageResource(images[position]);
        return view;
    }
}
