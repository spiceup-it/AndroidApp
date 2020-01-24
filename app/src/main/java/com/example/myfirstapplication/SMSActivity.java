package com.example.myfirstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.List;

public class SMSActivity extends AppCompatActivity {

    private EditText et1,et2;
    private Button btn;
    private TextView text1,text2;
    private ImageView imageView;

    private static final int REQ_PICK_CONTACT = 2 ;

    private BroadcastReceiver sentStatusReceiver, deliveryStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Log.d("lifecycle","Oncreate()");

        et1 = findViewById(R.id.editText_to);
        et2 = findViewById(R.id.editText_message);
        btn = findViewById(R.id.sendbtn);

        text1 = findViewById(R.id.message_status_text_view);
        text2 = findViewById(R.id.delivery_status_text_view);

        imageView = findViewById(R.id.img_contact);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent,2);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = et1.getText().toString();
                String message = et2.getText().toString();

                //validation
                if(phone.isEmpty()){
                    Toast.makeText(SMSActivity.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                }else{

                    SmsManager sms = SmsManager.getDefault();

                    List<String> messages = sms.divideMessage(message);
                    for(String msg : messages) {
                        PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(),0,new Intent("SMS SENT"),0);
                        PendingIntent deliveredIntent = PendingIntent.getBroadcast(getApplicationContext(),0,new Intent("SMS DELIVERED"),0);
                        sms.sendTextMessage(phone, null, message, sentIntent, deliveredIntent);
                       // text1.setText("SEND SUCCESSFULLY");
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_PICK_CONTACT){
            if(resultCode == RESULT_OK){
                Uri contactData = data.getData();
                Cursor cursor = managedQuery(contactData,null,null,null,null);
                cursor.moveToFirst();

                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                et1.setText(number);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","OnResume()");

        sentStatusReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String send_status = "Unknown Error";
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        send_status = "Message Sent Successfully";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        send_status = "Generic Failure";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        send_status = "No service available";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        send_status = "NULL PDU";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        send_status = "Error: Radio is off";
                        break;
                    default:
                        send_status = "Error";
                }

                text1.setText(send_status);
            }
        };

        deliveryStatusReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String delivery_status = "Message Not Delivered";
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        delivery_status = "Message Delivered Successfully";
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
                text2.setText(delivery_status);
                et1.setText("");
                et2.setText("");
            }
        };

        registerReceiver(sentStatusReceiver,new IntentFilter("SMS_SENT"));
        registerReceiver(deliveryStatusReceiver,new IntentFilter("SMS_DELIVERED"));
    }
}
