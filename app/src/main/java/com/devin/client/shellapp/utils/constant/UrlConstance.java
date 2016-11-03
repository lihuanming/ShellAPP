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
    public static final String PUBLIC_KEY ="http://user.tunnel.2bdata.com";
    public static final String APP_URL = "http://user.tunnel.2bdata.com/front/";

    //注册用户接口
    public static final String KEY_REGIST_INFO ="http://user.tunnel.2bdata.com/front/barker/user/api/register";

    //登录用户接口
    public static final String KEY_LOGIN_INFO ="http://user.tunnel.2bdata.com/front/barker/user/api/login_use_password";

    //获取用户基本信息
    public static final String KEY_USER_BASE_INFO ="user/user/api/user";
    //重置密码
    public static final String KEY_RET_PASSWORD="http://user.tunnel.2bdata.com/front/barker/user/api/update_password";
    //获取手机验证码
    public static final String KEY_PHONE_VERIFYKYCODE="http://user.tunnel.2bdata.com/front/barker/user/api/code";
}
