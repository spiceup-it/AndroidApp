package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.myfirstapplication.database_sqlite.DbHelper;

public class ModifyCountryActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name,et_desc;
    Button btn_update,btn_delete;

    private long _id;

    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_country);

        dbHelper = new DbHelper(this);
        dbHelper.open();

        et_name = findViewById(R.id.editText_name);
        et_desc = findViewById(R.id.editText_desc);

        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        et_name.setText(name);
        et_desc.setText(desc);

        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_update:
                        String country_name = et_name.getText().toString();
                        String country_currency = et_desc.getText().toString();

                        dbHelper.update(_id, country_name, country_currency);
                        this.returnHome();
                        break;
            case R.id.btn_delete:
                    dbHelper.delete(_id);
                    this.returnHome();
                    break;
        }
    }


    public void returnHome(){
        Intent home_intent = new Intent(this, CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
