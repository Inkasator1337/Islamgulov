package com.example.islamgulov;

import android.content.Context;
import android.os.Vibrator;

public class Transform {

    public static Boolean StringNoNull (String string)
    {
        if (string==null)
            return  false;
        else return string.length() != 0;
    }
    public static void Vibrate (Context context)
    {
        long mills = 1000L;
        Vibrator vibrator =(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator.hasVibrator())
        {
            vibrator.vibrate(mills);
        }
    }

    public static int parseIntOrDefault(String whatParse , int defaultValue){
        int parse;
        try {
            parse = Integer.parseInt(whatParse);

        }

        catch (Exception NumberFormatException)
        {
            parse = defaultValue;
        }

        return parse;

    }
}
