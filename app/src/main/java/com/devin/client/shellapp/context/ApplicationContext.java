package com.devin.client.shellapp.context;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Yatu on 2016/5/16.
 */
public class ApplicationContext extends Application{
    private static RequestQueue queues;//请求队列

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueues(){
        return queues;
    }
}
