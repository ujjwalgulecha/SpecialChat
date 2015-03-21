package com.codingwarriors.specialchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Ujjwal on 21-03-2015.
 */
public class ReceiveMessage extends Activity {
    float x1,x2;
    float y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receivemessagelayout);
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
}
