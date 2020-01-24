package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.*;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    EditText editText_user_input;
    Button btn_Speak;

    TextToSpeech convertToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        editText_user_input = findViewById(R.id.et_speech_input);
        btn_Speak = findViewById(R.id.btn_text_to_speech);

        btn_Speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_input = editText_user_input.getText().toString();
                if(user_input.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter some text!", Toast.LENGTH_SHORT).show();
                    return;
                }

                convertToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            convertToSpeech.setLanguage(Locale.US);
                            convertToSpeech.speak(user_input,TextToSpeech.QUEUE_FLUSH, null, null);

                        }
                    }
                });
            }
        });
    }
}
