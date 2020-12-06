package com.example.islamgulov;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;

import static com.example.islamgulov.UserStaticInfo.LOGIN;
import static com.example.islamgulov.UserStaticInfo.PASSWORD;

public class Transform {
    //it was file name of nastroek
    public static final String APP_REFERENCES = "mysettings";
    public static Boolean StringNoNull (String string)
    {
        if (string==null)
            return  false;
        else return string.length() != 0;
    }

    public static void SaveUser(SharedPreferences sp,String login, String password)
    {
        SharedPreferences.Editor e = sp.edit();
        e.putString(LOGIN, login);
        e.putString(PASSWORD, password);
        e.apply();
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
