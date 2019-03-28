package com.project.doer.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.doer.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;



public class AppUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }


    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }


    /**
     * Get formatted date.
     *
     * @param inputMillis  milliseconds
     * @param outPutFormat formats.
     * @see AppConstants.DateOutputFormat
     */
    public static String getFormattedDate(long inputMillis, String outPutFormat) {
        return new SimpleDateFormat(outPutFormat, Locale.ENGLISH).format(inputMillis);
    }

    /**
     * Get today's formatted date.
     *
     * @param outPutFormat formats.
     * @see AppConstants.DateOutputFormat
     */
    public static String getTodaysDate(String outPutFormat) {
        return new SimpleDateFormat(outPutFormat, Locale.ENGLISH).format(System.currentTimeMillis());
    }


}
