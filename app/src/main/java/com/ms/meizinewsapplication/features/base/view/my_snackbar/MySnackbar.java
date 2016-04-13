package com.ms.meizinewsapplication.features.base.view.my_snackbar;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * [Snack Bar](http://materialdoc.cn/snackbar/)
 * Created by 啟成 on 2016/3/2.
 */
public class MySnackbar {

    private static Snackbar snackBar;

    public static void ShowMySnackbar(
            @NonNull View view,
            @NonNull CharSequence text
    ) {
        ShowMySnackbar(
                view,
                text,
                "close",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackBar.dismiss(); //hide snackbar
                    }
                },
                Snackbar.LENGTH_LONG
        );
    }

    public static void ShowMySnackbar(
            @NonNull View view,
            @NonNull CharSequence text,
            CharSequence textAction,
            View.OnClickListener listener,
            int duration
    ) {
        snackBar = Snackbar.make(view, text, duration);

        snackBar.setAction(textAction, listener).show();
    }

}
