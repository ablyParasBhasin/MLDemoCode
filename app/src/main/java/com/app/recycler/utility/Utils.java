package com.app.recycler.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.app.recycler.R;

public class Utils {
    public static void enableScroll(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setMovementMethod(new ScrollingMovementMethod());
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    public static void showDialog(Context context, String msg) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setMessage(msg);
        b.setCancelable(false);

        b.setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = b.create();
        alert11.show();

        Button bq = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
        bq.setTextColor(ContextCompat.getColor(context, R.color.color_primary));
    }


}