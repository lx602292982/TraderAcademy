package com.traderacademy.http.listener;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.traderacademy.application.OverallApplication;

import java.util.Observer;

/**
 * Created by Admin on 2016/1/28.
 */
public class MyErrorListener implements Response.ErrorListener {
    private Observer observer;

    public MyErrorListener(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        getTrouble(volleyError);
        /*if(volleyError != null && !StringUtils.isBlank(volleyError.getMessage())) {
            Log.e("Error", volleyError.getMessage());
            Toast.makeText(OverallApplication.getContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
        observer.update(null, null);
    }

    public void getTrouble(VolleyError volleyError) {
        Log.e("Volley", "error is " + volleyError.fillInStackTrace().toString());
        String errType = volleyError.fillInStackTrace().toString();
        if (errType.indexOf("com.android.volley.ServerError") >= 0) {
            toast("服务器出现问题，请联系客服");
        } else if(errType.indexOf("com.android.volley.NoConnectionError") >= 0) {
            toast("网络链接错误，请检查手机网络");
        } else if(errType.indexOf("com.android.volley.TimeoutError") >=0) {
            toast("网络请求超时，请稍后再试");
        } else {
            toast("发生未知错误");
        }
    }

    private void toast(String s) {
        Toast.makeText(OverallApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
