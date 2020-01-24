package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.myfirstapplication.database_sqlite.DbHelper;

public class AddCountryActivtiy extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_name, editText_desc;
    private Button btn_add_record;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_country_activtiy);

        editText_name = findViewById(R.id.editText_country);
        editText_desc = findViewById(R.id.editText_desc);

        btn_add_record = findViewById(R.id.add_record);

        dbHelper = new DbHelper(this);
        dbHelper.open();
        btn_add_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_record:

                final String name = editText_name.getText().toString();
                final String desc = editText_desc.getText().toString();

                dbHelper.insert(name,desc);

                Intent intent = new Intent(AddCountryActivtiy.this, CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

               // Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
