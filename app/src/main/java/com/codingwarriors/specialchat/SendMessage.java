package com.codingwarriors.specialchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Ujjwal on 21-03-2015.
 */
public class SendMessage extends Activity {
    float x1,x2;
    float y1, y2;
    Button dot,dash,del,space;
    HashMap<String, String> codes = new HashMap<String, String>();
    TextToSpeech ttobj;

    public String answer ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessagelayout);
        codes.put(".-", "a");
        codes.put("-...", "b");
        codes.put("-.-.", "c");
        codes.put("-..", "d");
        codes.put(".", "e");
        codes.put("..-.", "f");
        codes.put("--.", "g");
        codes.put("....", "h");
        codes.put("..", "i");
        codes.put(".---", "j");
        codes.put("-.-", "k");
        codes.put(".-..", "l");
        codes.put("--", "m");
        codes.put("-.", "n");
        codes.put("---", "o");
        codes.put(".--.", "p");
        codes.put("--.-", "q");
        codes.put(".-.", "r");
        codes.put("...", "s");
        codes.put("-", "t");
        codes.put("..-", "u");
        codes.put("...-", "v");
        codes.put(".--", "w");
        codes.put("-..-", "x");
        codes.put("-.--", "y");
        codes.put("--..", "z");

        dot = (Button)findViewById(R.id.DotButton);
        dash = (Button)findViewById(R.id.DashButton);
        del = (Button)findViewById(R.id.DeleteButton);
        space = (Button)findViewById(R.id.SpaceButton);

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
    @Override
    public void onPause(){
        if(ttobj !=null){
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }


    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();


                /*if (x1 < x2)
                {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,SendMessage.class);
                    startActivity(i);
                }*/


                if (x1 > x2)
                {
                    Toast.makeText(this, "Back Home", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }
                break;
            }
        }
        return false;
    }
    public void speakText(View view){
        String toConvert = answer;
        String[] temporary;
        String delimiter = " ";
        String speak = "";
        temporary = toConvert.split(delimiter);
        for(int i =0; i < temporary.length ; i++)
        {
            speak +=codes.get(temporary[i]);
        }
        //String toSpeak = write.getText().toString();
        Toast.makeText(getApplicationContext(), speak,
                Toast.LENGTH_SHORT).show();
        ttobj.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
        answer= "";

    }

    public void DotClick(View view) {
        answer+=".";
    }

    public void DashClick(View view) {
        answer+="-";
    }

    public void SpaceClick(View view) {
        answer+=" ";
    }

    public void DeleteClick(View view) {
        String abc = answer.substring(0,answer.length()-1);
        answer = abc;
    }
}
