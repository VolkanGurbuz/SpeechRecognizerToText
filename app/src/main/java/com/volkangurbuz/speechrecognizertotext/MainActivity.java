package com.volkangurbuz.speechrecognizertotext;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //speech recognizer
    public Intent intent;
    public static final int request_code_voice = 1;
    String speechString = "";
    TextView speechWords;
    Button buttonToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechWords= findViewById(R.id.wordsFromSRec);
        buttonToSpeech = findViewById(R.id.buttonToSpeech);


    }


    public void sendVoiceMessage(View v) {
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // this is an intent for the recognize speech
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        try {
            //started to the activity with two paramters which is request code
            startActivityForResult(intent, request_code_voice);


        } catch (ActivityNotFoundException e) {
            // if no support there is an alertdialog for the message
            e.printStackTrace();
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Your mobil phone is not support yet the speech recognizer")
                    .setTitle("Error")
                    .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case request_code_voice: {

                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechString = speech.get(0);

                }
                break;
            }

        }
    }

}
