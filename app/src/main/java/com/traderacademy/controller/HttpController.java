package com.traderacademy.controller;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.traderacademy.application.OverallApplication;
import com.traderacademy.http.listener.ErrorListener;
import com.traderacademy.http.model.ResultParam;

/**
 * create by Eric
 * create date 2016/05/10
 */
public class HttpController {
    protected static int POST = Request.Method.POST;
    protected static int GET = Request.Method.GET;
    private static HttpController controller;

    protected static String unErrorString = "发生未知错误";

    protected static ErrorListener stringErrorListener  = new ErrorListener();

    private static HttpController getController() {
        if (controller == null) {
            controller = new HttpController();
        }
        return controller;
    }

    protected static void reportError(ResultParam rr) {
        if (rr != null) {
            reportError(rr.getErrorsStr());
        } else {
            reportError("Json 数据转换错误！");
        }
    }

    protected static void reportError(String err) {
        Toast.makeText(OverallApplication.getContext(), err, Toast.LENGTH_LONG).show();
    }
}
