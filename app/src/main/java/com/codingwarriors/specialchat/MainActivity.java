package com.codingwarriors.specialchat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity
{
    String msgData = " ";
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
                    Toast.makeText(this, "Send Message", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,SendMessage.class);
                    startActivity(i);
                }


                else if (x1 > x2 && (x1-x2)>(y1-y2))
                {
                    Toast.makeText(this, "Read Messages", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,ReceiveMessage.class);
                    i.putExtra("str","hello");
                    startActivity(i);
                }

                else if (y2 > y1 && (y2-y1)>(x2-x1))
                {
                    Toast.makeText(this, "Record Message", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,VoiceToText.class);
                    startActivity(i);
                }

                break;
            }
        }
        return false;
    }
  /*  public List<String> getAllSmsFromProvider() {
        List<String> lstSms = new ArrayList<String>();
        Context mActivity;
        ContentResolver cr = mActivity.getContentResolver();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                new String[] { Telephony.Sms.Inbox.BODY }, // Select body text
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                lstSms.add(c.getString(0));
                c.moveToNext();
            }
        } else {
            throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return lstSms;
    }*/


}
