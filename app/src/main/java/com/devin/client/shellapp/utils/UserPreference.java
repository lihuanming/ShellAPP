package com.devin.client.shellapp.utils;

import android.content.SharedPreferences;

import com.devin.client.shellapp.context.LibApplicationContext;

/**
 * 项目名称：ShellApp
 * 类描述：ShaerPreference保存用户登陆信息
 * 创建人：LHM
 * 创建时间：2016/10/28 15:00
 * 修改人：LHM
 * 修改时间：2016/10/28 15:00
 * 修改备注：
 */

public class UserPreference {
    private static SharedPreferences mUserPreferences;
    private static final String USER_PREFERENCE = "user_preference";

    public static SharedPreferences ensureIntializePreference() {
        if (mUserPreferences == null) {
            mUserPreferences = LibApplicationContext.getInstance().getSharedPreferences(USER_PREFERENCE, 0);
        }
        return mUserPreferences;
    }

    public static void save(String key, String value) {
        SharedPreferences.Editor editor = ensureIntializePreference().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String read(String key, String defaultvalue) {
        return ensureIntializePreference().getString(key, defaultvalue);
    }
}
