package com.traderacademy.http.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Type;
import java.util.Observer;

import com.traderacademy.application.OverallApplication;
import com.traderacademy.http.listener.ErrorListener;
import com.traderacademy.http.listener.StringListener;
import com.traderacademy.http.request.StringHttpRequest;
import com.traderacademy.http.utils.StringUtils;

/**
 * Created by Eric
 * on 2016/5/11
 * for project TRC
 */
public class RequestService {
    private static RequestQueue mQueue;

    public static RequestQueue getQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(OverallApplication.getContext());
        }
        return mQueue;
    }

    /**
     * 默认Get方法，不携带session
     * @param url           请求链接
     * @param observer      回调类
     * @param obj           请求参数类
     * @param type          返回获取的类型
     */
    public static void sendRequest(String url, Observer observer, Object obj, Type type) {
        StringListener sucListener = new StringListener(observer, type);
        ErrorListener errListener = new ErrorListener(observer);
        StringHttpRequest request = new StringHttpRequest(Request.Method.GET, url, obj, sucListener, errListener);
        getQueue().add(request);
    }

    /**
     * 请求方法，携带session
     * @param method        请求方法
     * @param url           请求链接
     * @param observer      回调类
     * @param obj           请求参数类
     * @param type          返回数据类型
     */
    public static void sendRequest(int method, String url, Observer observer, Object obj, Type type) {
        StringListener sucListener = new StringListener(observer, type);
        ErrorListener errListener = new ErrorListener(observer);
        StringHttpRequest request = new StringHttpRequest(method, url, obj, sucListener, errListener);
        sendRequest(request);
    }

    /**
     *
     * @param method        请求方法
     * @param url           请求链接
     * @param observer      回调类
     * @param obj           请求参数类
     * @param type          数据返回类
     * @param isAutoLogin   未登录状态是否自动登录
     * @param afterObs      自动登录完成后的回调类
     */
    public static void sendRequest(int method, String url, Observer observer, Object obj, Type type, boolean isAutoLogin, Observer afterObs) {
        StringListener sucListener = new StringListener(observer, type, isAutoLogin, afterObs);
        ErrorListener errListener = new ErrorListener(observer);
        StringHttpRequest request = new StringHttpRequest(method, url, obj, sucListener, errListener);
        sendRequest(request);
    }

    private static void sendRequest(StringHttpRequest request) {
        if(!StringUtils.isBlank(OverallApplication.cookie)) {
            request.setSendCookie(OverallApplication.cookie);
        }
        getQueue().add(request);
    }
}