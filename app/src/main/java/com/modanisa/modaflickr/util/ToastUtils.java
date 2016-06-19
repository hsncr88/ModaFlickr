package com.modanisa.modaflickr.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hasan ACAR on 04.08.2015.
 */
public class ToastUtils {
    public static final ToastUtils TOAST_LONG = new ToastUtils(Toast.LENGTH_LONG);
    public static final ToastUtils TOAST_SHORT = new ToastUtils(Toast.LENGTH_SHORT);

    private final int duration;

    private ToastUtils(int duration) {
        this.duration = duration;
    }

    public Toast makeText(Context context, String text) {
        return Toast.makeText(context, text, duration);
    }
    public Toast makeText(Context context, int resId) {
        return Toast.makeText(context, resId, duration);
    }

    public void show(Context context, String text) {
        makeText(context, text).show();
    }

    public void show(Context context, int resId) {
        makeText(context, resId).show();
    }



}