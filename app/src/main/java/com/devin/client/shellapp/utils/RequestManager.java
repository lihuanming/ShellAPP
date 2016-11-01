package com.devin.client.shellapp.utils;

import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devin.client.shellapp.context.LibApplicationContext;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * 项目名称：ShellApp
 * 类描述：网络请求处理
 * 创建人：LHM
 * 创建时间：2016/10/28 3:42
 * 修改人：LHM
 * 修改时间：2016/10/28 3:42
 * 修改备注：
 */

public class RequestManager {
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    private synchronized static void initRequestQueue() {
        if (mRequestQueue == null) {
            //创建一个请求队列
            mRequestQueue = Volley.newRequestQueue(LibApplicationContext.getInstance());
        }
    }

    /**
     * 添加请求到请求队列中
     *
     * @param request
     * @param tag
     */
    private static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    /**
     * post 请求数据
     *
     * @param method    提交的方法 post,get,put,delete
     * @param app_url   公共的接口前缀 http://172.18.8.56:8080/front/barker/api/
     * @param tag_url   接口名称，例如：register(注册接口)
     * @param parameter 请求参数封装对象
     * @param clazz     返回数据封装对象，如果传null，则直接返回String
     * @param callback  接口回调监听
     */
    public static <T> void post(final int method,final String app_url, final String tag_url, final HashMap<String, String> parameter, Class<T> clazz,
                                final HttpResponseCallBack callback) {
        //发送post请求服务器
        post(method,app_url, tag_url, parameter, clazz, callback, Priority.NORMAL);
    }


    /**
     * post 请求数据
     *
     * @param method    提交的方法 post,get,put,delete
     * @param app_url   路径
     * @param url       接口名称
     * @param parameter 请求参数封装对象
     * @param clazz     返回数据封装对象，如果传null，则直接返回String
     * @param callback  接口回调监听
     * @param priority  指定接口请求线程优先级
     */
    public static <T> void post(final int method,
                                final String app_url, final String url,
                                final HashMap<String, String> parameter,
                                final Class<T> clazz,
                                final HttpResponseCallBack callback, Priority priority) {
        if (callback != null) {
            callback.onResponeStart(url);//回调请求开始
        }

        initRequestQueue();

        //将公共的接口前缀和接口名称拼接
        //例如：拼接成注册的接口  http://172.18.8.56:8080/front/barker/api/register
        StringBuilder builder = new StringBuilder(app_url);
        builder.append(url);
        // 检查当前网络是否可用
        if (!NetworkUtils.isNetworkAvailable() && android.os.Build.VERSION.SDK_INT > 10) {
            if (callback != null) {
                callback.onFailure(url, null, 0, "网络出错···");//回调请求失败
                return;
            }
        }

        /**
         * 使用Volley框架去请求服务器
         * Method.POST：请求方式为post
         * builder.toString()：请求的链接
         * Listener<String>：监听
         */
        StringRequest request = new StringRequest(method, builder.toString(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        //这个位置先公共解析处理共同异常
                        try {
                            if (response != null && callback != null) {
                                Gson gson = new Gson();
                                //回调请求成功，同时url和解析的对象
                                callback.onSuccess(url, gson.fromJson(response, clazz));

                            }

                        } catch (Exception e) {
                            // TODO: handle exception
                            if (callback != null) {
                                //回调请求失败--解析异常
                                callback.onFailure(url, e, 0, "解析异常");
                                return;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            //请求出错的监听
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) {
                    if (error != null) {
                        callback.onFailure(url, error.getCause(), 0,
                                error.getMessage());
                    } else {
                        callback.onFailure(url, null, 0, "");
                    }
                }
            }
        }) {
            //post请求的参数信息
            protected Map<String, String> getParams() {
                return getPostApiParmes(parameter);
            }
        };
        //添加请求到请求队列中
        addRequest(request, builder.toString());
    }

    /*
     * post参数
	 *
	 * ts:时间戳
	 * sign: 接口签名
	 * parms = 按文档参数拼接 parm[0]+ … + parm[n-1] sign =
	 * md5(parms+"双方平台约定公钥")
	 */
    private static ApiParams getPostApiParmes(final HashMap<String, String> parameter) {
        ApiParams api = new ApiParams();
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            api.with(entry.getKey(), entry.getValue());
        }
        return api;
    }
}
