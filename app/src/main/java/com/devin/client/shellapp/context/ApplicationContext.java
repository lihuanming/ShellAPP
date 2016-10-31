package com.devin.client.shellapp.context;

import com.devin.client.shellapp.model.UserBaseInfo;
import com.devin.client.shellapp.utils.RequestApiData;

/**
 * 项目名称：ShellApp
 * 类描述：主要作用是处理一些app全局变量
 * 创建人：LHM
 * 创建时间：2016/10/28 16:17
 * 修改人：LHM
 * 修改时间：2016/10/28 16:17
 * 修改备注：
 */

public class ApplicationContext extends LibApplicationContext {
    private UserBaseInfo baseUser;//用户基本信息
    private static ApplicationContext instance;
    private RequestApiData requestApi;
    // 渠道号
    private String fid = "";
    // 版本号
    private String version = "";

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        requestApi= RequestApiData.getInstance();
    }

    public static void setInstance(ApplicationContext instance) {
        ApplicationContext.instance = instance;
    }

    /**
     * 设置用户信息
     *
     * @param baseUser
     */
    public void setBaseUser(UserBaseInfo baseUser) {
        this.baseUser = baseUser;
    }

    /**
     * 获取ApplicationContext实例
     *
     * @return
     */
    public static ApplicationContext getInstance() {
        return instance;
    }
}