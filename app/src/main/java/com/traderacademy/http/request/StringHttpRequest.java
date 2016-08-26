package com.traderacademy.http.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.traderacademy.application.OverallApplication;
import com.traderacademy.http.listener.ErrorListener;
import com.traderacademy.http.listener.StringListener;
import com.traderacademy.http.utils.RequestParamUtils;
import com.traderacademy.http.utils.StringUtils;

/**
 * create by Eric
 * create date 2016/05/10
 */
public class StringHttpRequest extends StringRequest {
    private Map<String, String> mMap;
    public String cookieFromResponse;
    private String mHeader;
    private Map<String, String> sendHeader=new HashMap<String, String>(1);
    private ErrorListener errListener;
    private StringListener sucListener;

    public StringHttpRequest(int method, String url, Object obj, StringListener sucListener, ErrorListener errListener) {
        super(method, url, sucListener, errListener);
        setRetryPolicy( new DefaultRetryPolicy( 1000*15,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ) );
        this.sucListener = sucListener;
        this.errListener = errListener;
        VolleyError volleyError = null;
        try {
            if (obj != null) {
                mMap = RequestParamUtils.loadRequestParam(obj, false);
            } else {
                mMap = new HashMap<String, String>();
            }
        } catch (NoSuchMethodException e) {
            volleyError = new VolleyError(e.getCause());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            volleyError = new VolleyError(e.getCause());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            volleyError = new VolleyError(e.getCause());
            e.printStackTrace();
        } finally {
            if (volleyError != null) {
                errListener.onErrorResponse(volleyError);
            }
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            mHeader = response.headers.toString();
            Pattern pattern=Pattern.compile("Set-Cookie.*?;");
            Matcher m=pattern.matcher(mHeader);
            if(m.find()){
                cookieFromResponse =m.group();
            }
            if(!StringUtils.isBlank(cookieFromResponse)) {
                cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);
                OverallApplication.cookie = new String(cookieFromResponse);
            }
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            //String jsonObject = new String(jsonString);
            //jsonObject.put("Cookie",cookieFromResponse);
            //Log.w("LOG","jsonObject "+ jsonObject.toString());
            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        sucListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return sendHeader;
    }
    public void setSendCookie(String cookie){
        sendHeader.put("Cookie",cookie);
    }

    public Response.Listener<String> initSuccessListener(Observer observer, Type type, Observer afterObs) {
        if (afterObs == null) {
            sucListener = new StringListener(observer, type);
        } else {
            sucListener = new StringListener(observer, type, true, afterObs);
        }
        return sucListener;
    }
}
