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

    private RequestApiData() {
    }

    //创建接口对象
    public static RequestApiData getInstance() {
        if (instance == null) {
            synchronized (RequestApiData.class) {
                if (instance == null) {
                    instance = new RequestApiData();
                }
            }
        }
        return instance;
    }

    /**
     * 注册用户接口
     *
     * @param name     用户昵称
     * @param email    注册邮箱
     * @param password 注册密码
     * @param clazz    数据返回的对象
     * @param callBack 回调
     *                 要注意，参数的位置不能变，要根据文档来
     *                 请求方式：POST
     */
    public void getRegisterData(String name, String email, String password,
                                Class<AnalyticalRegistInfo> clazz, HttpResponeCallBack callBack) {
        mCallBack = callBack;
        //这是每一接口的唯一标示
        String tagUrl = UrlConstance.KEY_REGIST_INFO;//注册接口
        //将注册信息保存在Map中（且必须与服务器一致）
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("name", name);
        parameter.put("email", email);
        parameter.put("password", password);

        //拼接参数信息，昵称、邮箱、密码、公钥，并用md5进行加密
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(email);
        builder.append(password);
        builder.append(UrlConstance.PUBLIC_KEY);

        parameter.put(UrlConstance.ACCESSTOKEN_KEY, MD5Util.getMD5Str(builder.toString()));
        //请求接口
        RequestManager.post(Request.Method.POST, UrlConstance.APP_URL, tagUrl, parameter, clazz, callBack);
    }


    /**
     * 登陆用户接口
     * @param email 邮箱
     * @param password 密码
     * @param clazz 数据返回的解析对象
     * @param callback 回调
     * 注意参数的位置不能改变，按照文档来
     * 请求方式：POST
     */
    public void getLoginData(String email ,String password,
                             Class<UserBaseInfo> clazz,
                             HttpResponeCallBack callback) {
        mCallBack = callback;
        //这是每一个接口的唯一标示
        String tagUrl = UrlConstance.KEY_LOGIN_INFO;//登录接口
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("email", email);
        parameter.put("password", password);

        //拼接参数信息，邮箱，密码，公钥，并用md5进行加密
        StringBuilder builder = new StringBuilder();
        builder.append(email);
        builder.append(password);
        builder.append(UrlConstance.PUBLIC_KEY);

        parameter.put(UrlConstance.ACCESSTOKEN_KEY, MD5Util.getMD5Str(builder.toString()));

        //请求数据接口
        RequestManager.post(Request.Method.POST, UrlConstance.APP_URL,tagUrl, parameter, clazz, callback);

    }
}
