package com.example.myfirstapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

public class BasicActivity extends AppCompatActivity implements View.OnClickListener  {

    AlertDialog.Builder builder;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    TextView textView,txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);



         txt1= findViewById(R.id.txt1);
         txt1.setText("Hello");


        builder = new AlertDialog.Builder(this);
        ImageView imageView = findViewById(R.id.imageView2);
       // imageView.setImageResource(R.drawable.pic3);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout rlayout = findViewById(R.id.relativeLayout);
        //rlayout.setBackgroundColor(Color.GRAY);
       // rlayout.setBackgroundResource(R.drawable.pic3);

        EditText text1 = findViewById(R.id.et1);
        EditText text2 = findViewById(R.id.et2);
        final Button button = findViewById(R.id.button1);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  Toast.makeText(BasicActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
//
//
//                button.setText("Submit Again");
//
//                CheckBox cb = findViewById(R.id.checkBox);
//                if(cb.isChecked()){
//                    ImageView imv = findViewById(R.id.imageView);
//                    int flag=0;
//                    if(flag == 0) {
//                        imv.setImageResource(R.drawable.pic1);
//                        flag++;
//                    }
//                    else if(flag == 1) {
//                        imv.setImageResource(R.drawable.pic3);
//                        flag++;
//                    }
//                    else if(flag == 2){
//                        imv.setImageResource(R.drawable.pic5);
//                        flag++;
//                    }
//
//                }
//            }
//        });

//        button.setOnClickListener(new ButtonClick());
//
       // button.setOnClickListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Snackbar message", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(BasicActivity.this, "My First Toast Message", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    public void clicked(View v){
        switch (v.getId()){
            case R.id.button1:

                Intent intent = new Intent(this, MainActivity.class);
                EditText editText = (EditText) findViewById(R.id.et3);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
               // Toast.makeText(this, "Submit button is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                //Uncomment the below code to Set the message and title from the strings.xml file
               // builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                     .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
            });

                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();
                // Toast.makeText(this, "Cancel button is clicked", Toast.LENGTH_SHORT).show();
              //  break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Selected Item: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        switch(item.getItemId()){
            case R.id.search_item:
                Toast.makeText(this, "Searching....", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share_item:
                Toast.makeText(this, "Share....", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.close_item:
                //Toast.makeText(this, "Searching....", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

class ButtonClick implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }
}
