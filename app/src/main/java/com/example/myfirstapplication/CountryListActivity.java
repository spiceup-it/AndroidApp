package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.myfirstapplication.database_sqlite.DbHelper;
import com.example.myfirstapplication.database_sqlite.DbManager;

public class CountryListActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {
            DbManager._ID,
            DbManager.NAME,
            DbManager.DESCRIPTION
    };

    final int[] to = new int[]{ R.id.id, R.id.name, R.id.desc};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        dbHelper = new DbHelper(this);
        dbHelper.open();
        Cursor cursor = dbHelper.fetch();

        listView = findViewById(R.id.country_list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this,R.layout.country_view_record,cursor,from,to,0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView txt_id = view.findViewById(R.id.id);
                TextView txt_name = view.findViewById(R.id.name);
                TextView txt_desc = view.findViewById(R.id.desc);

                String id = txt_id.getText().toString();
                String name = txt_name.getText().toString();
                String desc = txt_desc.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("desc", desc);

                startActivity(intent);
            }
        });
    }
}
