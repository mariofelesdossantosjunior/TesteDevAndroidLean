package br.com.leanwork.testedevandroidlean.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ServiceUtil {

    public static void esconderTeclado(Context context, View view) {
        if (context != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
