package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textView;
    EditText editText;
    Spinner spinner;

    String[] country = {"India","Japan","China","USA","other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(ActivityLifecycle.EXTRA_MESSAGE);
//
//        textView = findViewById(R.id.textView1);
//        textView.setText(message);

        editText = findViewById(R.id.editText);




        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);



        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String URL = editText.getText().toString();
//                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
//                startActivity(i);

                String data = editText.getText().toString();
                Intent i = new Intent();
                i.putExtra("MESSAGE",data);
                setResult(2,i);
                finish();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, country[position], Toast.LENGTH_SHORT).show();
      //  textView.setText(country[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please Select something", Toast.LENGTH_SHORT).show();
    }
}
