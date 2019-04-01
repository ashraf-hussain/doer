package com.project.doer.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.project.doer.R;

import java.text.SimpleDateFormat;
import java.util.Locale;



public class AppUtils {


    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static void showSnackbar(Context context,View view, String text) {
        Snackbar snackbar = Snackbar
                .make(view, text, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        snackbar.show();
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
