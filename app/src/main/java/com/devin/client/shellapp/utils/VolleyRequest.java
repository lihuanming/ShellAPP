package com.devin.client.shellapp.utils;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.devin.client.shellapp.context.ApplicationContext;


import java.util.Map;
/**
 * Created by Yatu on 2016/5/17.
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    public static void requestGet(Context context,String url, String tag, VolleyInterface vif)
    {

        ApplicationContext.getQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,vif.loadingListener(),vif.errorListener());
        stringRequest.setTag(tag);
        ApplicationContext.getQueues().add(stringRequest);
    }

    public static void requestPost(Context context,String url, String tag,final Map<String, String> params, VolleyInterface vif)
    {
        ApplicationContext.getQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST,url,vif.loadingListener(),vif.errorListener())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        ApplicationContext.getQueues().add(stringRequest);
    }
}
