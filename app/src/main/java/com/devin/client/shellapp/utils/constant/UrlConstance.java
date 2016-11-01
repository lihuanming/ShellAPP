package com.devin.client.shellapp.utils.constant;

/**
 * @author LHM
 * @version $Rev$
 * @time 2016/10/28 2:25
 * @des 处理网络的参数常量类
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class UrlConstance {
    //访问令牌
    public static final String ACCESSTOKEN_KEY ="accesstoken";

    //签约公钥，即客户端与服务器协商订的一个公钥
    public static final String PUBLIC_KEY ="http://119.29.161.112:8086";
    public static final String APP_URL = "http://119.29.161.112:8086/front/barker/user/api/";

    //注册用户接口
    public static final String KEY_REGIST_INFO ="register";

    //登录用户接口
    public static final String KEY_LOGIN_INFO ="login";

    //获取用户基本信息
    public static final String KEY_USER_BASE_INFO ="user";
    //获取手机验证码
    public static final String KEY_PHONE_VERIFYKYCODE="http://119.29.161.112:8086/front/barker/user/api/code";
}
