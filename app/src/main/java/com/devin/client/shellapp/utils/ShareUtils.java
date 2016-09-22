package com.devin.client.shellapp.utils;

import android.content.Context;
import android.content.Intent;

import com.devin.client.shellapp.R;

/**
 * Created by 书凡 on 2015-11-23.
 */
public class ShareUtils {

    public static void share(Context context) {
        share(context, context.getString(R.string.about_share_text));
    }

    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.about_menu_action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, context.getString(R.string.about_menu_action_share)));
    }
}

