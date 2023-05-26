package com.example.reminisce_ticketing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static void hideKeyBoard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(View view) {
        showSoftKeyboard(view, null);
    }

    public static void showSoftKeyboard(View view, ResultReceiver resultReceiver) {
        Configuration config = view.getContext().getResources().getConfiguration();
        if (config.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (resultReceiver != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT, resultReceiver);
            } else {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    /**
     * ***************************************************************************
     * ******************************* DATE UTILS ********************************
     * ***************************************************************************
     */
    public static String GetDateOnRequireFormat(String date, String givenformat, String resultformat) {
        String result = "";
        SimpleDateFormat sdf;
        SimpleDateFormat sdf1;
        try {
            if (date != null) {
                sdf = new SimpleDateFormat(givenformat, Locale.US);
                sdf1 = new SimpleDateFormat(resultformat, Locale.US);
                result = sdf1.format(sdf.parse(date));
            }
            //.parse(str.replaceAll("(?<=\\d)(st|nd|rd|th)", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            sdf = null;
            sdf1 = null;
        }
        return result;
    }

    public static String GetDateOnRequireUTCDateFormat(String date, String givenformat, String resultformat) {
        String result = "";
        SimpleDateFormat sdf;
        SimpleDateFormat sdf1;
        try {
            if (date != null) {
                sdf = new SimpleDateFormat(givenformat, Locale.US);
                sdf1 = new SimpleDateFormat(resultformat, Locale.US);
                sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
                result = sdf1.format(sdf.parse(date));
            }
            //.parse(str.replaceAll("(?<=\\d)(st|nd|rd|th)", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            sdf = null;
            sdf1 = null;
        }
        return result;
    }

    public static void makeToast(Context ctx, String text) {
        if (!isEmpty(text)) {
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }


}
