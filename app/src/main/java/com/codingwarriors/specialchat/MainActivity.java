package com.codingwarriors.specialchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;


public class MainActivity extends Activity
{
    float x1,x2;
    float y1, y2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
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


                if (x1 < x2 && (x2-x1)>(y2-y1))
                {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,SendMessage.class);
                    startActivity(i);
                }


                else if (x1 > x2 && (x1-x2)>(y1-y2))
                {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,ReceiveMessage.class);
                    startActivity(i);
                }

                else if (y2 > y1 && (y2-y1)>(x2-x1))
                {
                    Toast.makeText(this, "Swipe Down", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,VoiceToText.class);
                    startActivity(i);
                }

                break;
            }
        }
        return false;
    }


}
