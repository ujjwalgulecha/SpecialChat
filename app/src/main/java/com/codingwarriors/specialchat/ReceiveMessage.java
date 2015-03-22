package com.codingwarriors.specialchat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ujjwal on 21-03-2015.
 */
public class ReceiveMessage extends Activity {

    public String p;
    private Button btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    TextToSpeech ttobj;
    public String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receivemessagelayout);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("str");

        btnSpeak = (Button)findViewById(R.id.button);

        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.US);
                        }
                        btnSpeak.setOnClickListener ( new View.OnClickListener()
                        {
                            @Override
                            public void onClick ( View view )
                            {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                ttobj.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });


                        btnSpeak.post(new Runnable(){
                            @Override
                            public void run() {
                                btnSpeak.performClick();
                            }
                        });;

                    }
                });



    }

}

