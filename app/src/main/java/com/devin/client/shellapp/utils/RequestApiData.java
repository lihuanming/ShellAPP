package com.devin.client.shellapp.utils;

import com.android.volley.Request;
import com.devin.client.shellapp.model.AnalyticalRegistInfo;
import com.devin.client.shellapp.model.UserBaseInfo;
import com.devin.client.shellapp.utils.constant.UrlConstance;

import java.util.HashMap;

/**
 * @author LHM
 * @version $Rev$
 * @time 2016/10/28 3:08
 * @des 网络接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class RequestApiData {
    private volatile static RequestApiData instance = null;
    private HttpResponeCallBack mCallBack = null;

    //创建接口对象
    public static RequestApiData getInstance() {
        if (instance == null) {
            instance = new RequestApiData();
        }
        return instance;
    }


    /**
     * 注册用户接口
     *
     * @param phone         手机号码
     * @param password      密码
     * @param clazz         返回对象
     * @param callback      回调
     *                      要注意，参数的位置不能变，要根据文档来
     *                      请求方式：POST
     */
    public void getRegisterData(String phone, String password,
                                Class<AnalyticalRegistInfo> clazz, HttpResponeCallBack callback) {
        mCallBack = callback;
        //这是每一接口的唯一标示

        String tagUrl = UrlConstance.KEY_REGIST_INFO;//注册接口

        //将注册信息保存在Map中（且必须与服务器一致）
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("phone", phone);
        parameter.put("password", password);

        //拼接参数信息，昵称、邮箱、密码\验证码、公钥，并用md5进行加密
        StringBuilder builder = new StringBuilder();
        builder.append(phone);
        builder.append(password);
        builder.append(UrlConstance.PUBLIC_KEY);

        parameter.put(UrlConstance.ACCESSTOKEN_KEY, MD5Util.getMD5Str(builder.toString()));
        //调用RequestManager的post方法，请求服务器
        RequestManager.post(UrlConstance.APP_URL,tagUrl, parameter, clazz, callback);
    }


    /**
     * 登陆用户接口
     * @param phone         手机号码
     * @param password      密码
     * @param clazz         数据返回的解析对象
     * @param callback      回调
     *                      注意参数的位置不能改变，按照文档来
     *                      请求方式：POST
     */
    public void getLoginData(String phone ,String password,
                             Class<UserBaseInfo> clazz,
                             HttpResponeCallBack callback) {
        mCallBack = callback;
        //这是每一个接口的唯一标示
        String tagUrl = UrlConstance.KEY_LOGIN_INFO;//登录接口
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("phone", phone);
        parameter.put("password", password);

        //拼接参数信息，邮箱，密码，公钥，并用md5进行加密
        StringBuilder builder = new StringBuilder();
        builder.append(phone);
        builder.append(password);
        builder.append(UrlConstance.PUBLIC_KEY);

        parameter.put(UrlConstance.ACCESSTOKEN_KEY,MD5Util.getMD5Str(builder.toString()));

        //请求数据接口
        RequestManager.post(UrlConstance.APP_URL,tagUrl, parameter, clazz, callback, Request.Priority.NORMAL);

    }

    /**
     * 重置密码接口
     *
     *
     * @param phone         手机号码
     * @param password      密码
     * @param verifyCode    验证码
     * @param clazz         返回对象
     * @param callback      回调
     *                      要注意，参数的位置不能变，要根据文档来
     *                      请求方式：POST
     */
    public void getResetPasswordData( String phone, String password, String verifyCode,
                                Class<AnalyticalRegistInfo> clazz, HttpResponeCallBack callback) {
        mCallBack = callback;
        //这是每一接口的唯一标示
        String tagUrl = UrlConstance.KEY_REGIST_INFO;//注册接口
        //将注册信息保存在Map中（且必须与服务器一致）
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("phone", phone);
        parameter.put("password", password);
        parameter.put("code", verifyCode);

        //拼接参数信息，昵称、邮箱、密码\验证码、公钥，并用md5进行加密
        StringBuilder builder = new StringBuilder();
        builder.append(phone);
        builder.append(password);
        builder.append(verifyCode);
        builder.append(UrlConstance.PUBLIC_KEY);

        parameter.put(UrlConstance.ACCESSTOKEN_KEY, MD5Util.getMD5Str(builder.toString()));
        //请求接口
        RequestManager.post(UrlConstance.APP_URL, tagUrl, parameter, clazz, callback, Request.Priority.NORMAL);
    }

    /**
     *
     * 获取手机验证码
     */
    public void getPhoneVerifyCode(String phone,  HttpResponeCallBack callBack){
        mCallBack=callBack;
        String tagUrl=UrlConstance.KEY_PHONE_VERIFYKYCODE;//获取验证码入口
    }
}
