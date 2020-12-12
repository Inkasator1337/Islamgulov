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
    public static Bitmap getRoundedCornerCubeBitmap(Bitmap bitmap,float radiusInPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, radiusInPx, radiusInPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getRoundedMapBitmap(Bitmap bitmap) {
        int iconSize = 100;
        Bitmap output = Bitmap.createScaledBitmap(bitmap, iconSize, iconSize, false);
        return getRoundedCornerCubeBitmap(output,iconSize);
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


    private static class Bitmap {
        public void getWidth() {
        }

        public void getHeight() {
        }
    }
}


