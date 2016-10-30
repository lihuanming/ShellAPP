package com.devin.client.shellapp.utils;

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
     * 验证密码(不包含特殊字符)6-16个密码
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
