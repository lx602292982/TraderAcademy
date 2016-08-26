package com.traderacademy.http.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.traderacademy.http.requestmodel.MultipartRequestParams;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * create by Eric
 * create date 2016/05/10
 * 文件上传请求类
 */
public class MultipartRequest extends Request<String> {
    private Response.ErrorListener errorListener = null;
    private Response.Listener mListener = null;
    private MultipartRequestParams params = null;
    private HttpEntity httpEntity = null;
    private Map<String, String> mHeaders=new HashMap<String, String>(1);

    public MultipartRequest(int method,MultipartRequestParams params, String url,Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.params = params;
        this.mListener = listener;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(params != null) {
            httpEntity = params.getEntity();
            try {
                httpEntity.writeTo(baos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String str = new String(baos.toByteArray());
        }
        return baos.toByteArray();
    }

    public String getBodyContentType() {
        // TODO Auto-generated method stub
        String str = httpEntity.getContentType().getValue();
        return httpEntity.getContentType().getValue();
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }


    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
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
}
