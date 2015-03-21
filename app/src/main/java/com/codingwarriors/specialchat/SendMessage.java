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

        //Morse code conversion standard
        codes.put(".----", "1");
        codes.put("..---", "2");
        codes.put("...--", "3");
        codes.put("....-", "4");
        codes.put(".....", "5");
        codes.put("-....", "6");
        codes.put("--...", "7");
        codes.put("---..", "8");
        codes.put("----.", "9");
        codes.put("-----", "0");
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
        codes.put(" ", " ");

        //Buttons defined by ID
        dot = (Button)findViewById(R.id.DotButton);
        dash = (Button)findViewById(R.id.DashButton);
        del = (Button)findViewById(R.id.DeleteButton);
        space = (Button)findViewById(R.id.SpaceButton);

        //configuring text to speech
        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.US);
                        }
                    }
                });

        //On long clicks space to invoke text to speech method
        space.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(SendMessage.this,MainActivity.class);
                startActivity(i);
                return true;
            }
        });

        //On long clicks dot to invoke text to speech method
        dot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String toConvert = answer;
                String[] words;
                String speak = "";
                words = toConvert.split("  ");
                for(int i =0; i < words.length ; i++)
                {
                    String[] letters;
                    String delimiter2 = " ";
                    letters = words[i].split(delimiter2);
                    for(int j=0;j<letters.length;j++)
                    {
                        speak += codes.get(letters[j]);
                    }
                    speak+=" ";
                }

                //String toSpeak = write.getText().toString();
                Toast.makeText(getApplicationContext(), speak,
                        Toast.LENGTH_SHORT).show();
                ttobj.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
                answer= "";
                SMS sender = new SMS();
                String phno = "9008080135";
                sender.sendSMS(phno,speak);
                return true;
            }
        });

        //On long clicks dash to invoke text to speech method
        dash.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String toConvert = answer;
                String[] words;
                String speak = "";
                words = toConvert.split("  ");
                for(int i =0; i < words.length ; i++)
                {
                    String[] letters;
                    String delimiter2 = " ";
                    letters = words[i].split(delimiter2);
                    for(int j=0;j<letters.length;j++)
                    {
                        speak += codes.get(letters[j]);
                    }
                    speak+=" ";
                }
                //String toSpeak = write.getText().toString();
                Toast.makeText(getApplicationContext(), speak,
                        Toast.LENGTH_SHORT).show();
                ttobj.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
                answer= "";
                SMS sender = new SMS();
                String phno = "9008080135";
                sender.sendSMS(phno,speak);
                return true;
            }
        });

        //Clear message when delet is long pressed
        del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                answer= "";
                return true;
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