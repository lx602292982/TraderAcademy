package com.traderacademy.http.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 可携带session的请求方法类
 * create by Eric
 * create date 2016/05/10
 */
public class CookieRequest extends StringRequest {

    private Map<String, String> mHeaders=new HashMap<String, String>(1);
    private Map<String, String> mMap;

    public CookieRequest(String url, Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public CookieRequest(int method, String url, Response.Listener<String> listener,
                         Response.ErrorListener errorListener, Map<String, String> mMap) {
        super(method, url, listener, errorListener);
        this.mMap = mMap;
    }

    /**
     * 对请求设置cookie
     * @param cookie
     */
    public void setCookie(String cookie){
        mHeaders.put("Cookie", cookie);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }

    //当http请求是post时，则需要该使用该函数设置往里面添加的键值对
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }
}
