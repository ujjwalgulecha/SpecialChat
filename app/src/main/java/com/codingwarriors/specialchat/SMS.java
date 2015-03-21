package com.codingwarriors.specialchat;

import android.app.Activity;
import android.telephony.SmsManager;

public class SMS extends Activity
{
    String phoneNo,message;
    /** Called when the activity is first created. */

    //---sends an SMS message to another device---
    public void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
/*

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);
*/

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        System.out.println("Ph : " + phoneNumber + "\nMessage :" +message);
    }
}