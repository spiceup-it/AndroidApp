package com.example.myfirstapplication;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.ViewHolder> {

    private List<Product> productList;
    Activity activity;

    public RecyclerAdaper(List<Product> products, Activity activity) {
        productList = products;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.row_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaper.ViewHolder holder, final int position) {

       // final String name = values.get(position);
        holder.imageView.setImageResource(productList.get(position).getImage());
        holder.textFooter.setText(productList.get(position).getSubtitle());
        holder.textHeader.setText(productList.get(position).getTitle());
        holder.textHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

    }

    private void remove(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textHeader;
        public TextView textFooter;
        public ImageView imageView;
        public View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            textHeader = v.findViewById(R.id.first_line_text);
            textFooter = v.findViewById(R.id.second_line_text);
            imageView = v.findViewById(R.id.icon);
        }
    }

}


