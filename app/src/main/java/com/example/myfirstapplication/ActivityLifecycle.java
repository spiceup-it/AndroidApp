package com.example.myfirstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLifecycle extends AppCompatActivity {

    Button button1;
    EditText editText;
    TextView textView;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d("lifecycle","OnCreate Invoked");

        button1 = findViewById(R.id.button1);
        editText = findViewById(R.id.editText1);
        textView = findViewById(R.id.textView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send data from Activity1 to Activity2
//                Intent i = new Intent(ActivityLifecycle.this,BasicActivity.class);
//                String message = editText.getText().toString();
//                i.putExtra(EXTRA_MESSAGE,message);
//                startActivity(i);

                //Implicit Intent
//                String url = editText.getText().toString();
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);

                //Start Activity for Result - Activity1 Get data from Activity2
//                Intent intent = new Intent(ActivityLifecycle.this,MainActivity.class);
//                startActivityForResult(intent,2);

                //Custom Toast Demo
                Intent intent = new Intent(ActivityLifecycle.this,CustomToast.class);
                startActivity(intent);


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 2){
            String message = data.getStringExtra("MESSAGE");
            textView.setText(message);
           // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","OnStart Invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","OnStop Invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","OnDestroyed Invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","OnPause Invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","OnResume Invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","OnRestart Invoked");
    }
}
