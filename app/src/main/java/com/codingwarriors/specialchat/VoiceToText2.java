package com.codingwarriors.specialchat;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by ACER on 3/22/2015.
 */
public class VoiceToText2 extends Activity {
    public String message;
    TextToSpeech ttobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voicetotextlayout2);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("p");

        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.US);
                        }
                    }
                });

    }

    public void SendClick(View view) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        ttobj.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        //sendSMS(String phoneNumber, String message)
        SMS sender = new SMS();
        String phno = "9008080135";
        sender.sendSMS(phno,message);
    }
}
