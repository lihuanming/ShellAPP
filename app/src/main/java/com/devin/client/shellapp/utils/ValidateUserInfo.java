package com.devin.client.shellapp.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 昵称、邮箱地址、密码格式校验
 */
public class ValidateUserInfo {
    /**
     * 验证用户名(不包含中文和特殊字符)5-17个字符
     * @param name
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isNameValid(String name) {
        String nPattern="^[a-zA-Z]\\w{5,17}$";
        Pattern p=Pattern.compile(nPattern);
        Matcher m=p.matcher(name);
        return m.matches();
    }
    /**
     * 验证手机格式
     */
    public static boolean isMobileValid(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 邮箱校验
     * @param email
     * @return
     */

    public static boolean isEmailValid(String email) {
        //TODO change for your own logic
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 验证密码(不包含特殊字符)6-32个密码
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password) {
        //TODO change for your own logic
        String pPattern="^[a-zA-Z0-9]{6,16}$";
        Pattern p=Pattern.compile(pPattern);
        Matcher m=p.matcher(password);
        return m.matches();
    }
}
