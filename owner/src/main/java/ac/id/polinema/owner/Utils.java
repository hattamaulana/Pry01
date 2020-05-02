package ac.id.polinema.owner;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    private static String TAG = Utils.class.getSimpleName();

    public static void showMessage(Context context, String message) {
        View view = ((Activity) context).findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public static String getDateTimeReadable(String timestamps, Integer index) {
        Log.i(TAG, "getDateTimeReadable: timestamps=" + timestamps);
        String[] split = timestamps.split("T");
        String date = split[0];
        String time = split[1].split("\\.")[0];
        if (index != null) {
            return index == 0 ? date : time;
        }

        return date + " " + time;
    }

    public static String getDateReadable(String timestamp) {
        if (timestamp.isEmpty()) return timestamp;
        return getDateTimeReadable(timestamp, 0);
    }

    public static String getTimeReadable(String timestamp) {
        if (timestamp.isEmpty()) return timestamp;
        return getDateTimeReadable(timestamp, 1);
    }
}