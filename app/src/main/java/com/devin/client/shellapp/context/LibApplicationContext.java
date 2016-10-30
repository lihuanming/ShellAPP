package com.devin.client.shellapp.context;

import android.app.Application;

public class LibApplicationContext extends Application {
    private static LibApplicationContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }

    private void setInstance(LibApplicationContext instance) {
        LibApplicationContext.instance = instance;
    }

    /**
     * 获取时间截
     *
     * @return
     */
    public String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取ApplicationContext实例
     *
     * @return
     */
    public static LibApplicationContext getInstance() {
        return instance;
    }

}
