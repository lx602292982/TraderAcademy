package com.traderacademy.http.listener;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.traderacademy.application.OverallApplication;
import com.traderacademy.controller.UserController;
import com.traderacademy.domain.LoginParam;
import com.traderacademy.domain.UserInfo;
import com.traderacademy.http.modelImpl.ResultParamImpl;
import com.traderacademy.http.utils.GsonUtils;
import com.traderacademy.http.utils.StringUtils;

import java.lang.reflect.Type;
import java.util.Observable;
import java.util.Observer;


/**
 * 带自动重新登录功能的监听类
 * create by Eric
 * create date 2016/05/10
 */
public class StringListener implements Response.Listener<String> {
    private ResultParamImpl resultParam;
    //session过期后自动登录，登录成功之后做的事情
    private Observer afterObs;
    //session未过期的情况
    private Observer observer;
    //未登录状态是否自动登录
    private boolean isAutoLogin;
    private Type typeOf;

    private static String unErrorString = "发生未知错误";

    protected static void reportError(ResultParamImpl rr) {
        if (rr != null) {
            reportError(rr.getErrorsStr());
        } else {
            reportError("Json 数据转换错误！");
        }
    }

    protected static void reportError(String err) {
        Toast.makeText(OverallApplication.getContext(), err, Toast.LENGTH_LONG).show();
    }

    public StringListener(Observer observer, Type typeOfT, boolean isAutoLogin, Observer afterObs) {
        this.afterObs = afterObs;
        this.observer = observer;
        this.isAutoLogin = isAutoLogin;
        this.typeOf = typeOfT;
    }

    public StringListener(Observer observer, Type typeOfT) {
        this.observer = observer;
        this.typeOf = typeOfT;
        this.isAutoLogin = false;
        this.afterObs = null;
    }

    @Override
    public void onResponse(String s) {
        //将json数据自动注入到制定模型中
        Log.e("TAG", "response is :" + s);
        try {
            resultParam = GsonUtils.asEntity(s, typeOf);
            //自动注入失败
            if(resultParam == null) {
                reportError(resultParam);
                //为防止程序锁死，必须调用回调函数
                observer.update(null, null);
            }else if(resultParam.isStatus()) {//注入成功且返回内容正确
                observer.update(null, resultParam);
            } else if (resultParam.isUnLoginStatus() && isAutoLogin && afterObs != null) {//提示请先登录且需要自动登录
                LoginParam lp = OverallApplication.getLoginParam();
                if(lp == null) {
                    reportError("请先登录");
                    //防止程序锁死
                    observer.update(null, null);
                    return;
                }
                //这里开始自动登录
                UserController.loginToMain(lp, new Observer() {
                    @Override
                    public void update(Observable observable, Object data) {
                        UserInfo r = (UserInfo) data;
                        if (r == null) {
                            reportError(r);
                            update(null, null);
                        } else {
                            //自动登录成功后回调
                            if (r.isStatus()) {
                                afterObs.update(null, r);
                            }
                        }
                    }
                });
            } else if(!resultParam.isStatus() && !resultParam.isUnLoginStatus() && !StringUtils.isBlank(resultParam.getMessage())) {
                //注入成功,但发生无法预测的事情时
                reportError(resultParam.getMessage());
                observer.update(null, resultParam);
            } else if(resultParam.isUnLoginStatus() && !isAutoLogin) {
                //显示未登录，且不自动登录
                reportError(resultParam.getMessage());
                observer.update(null, null);
            } else {
                observer.update(null, resultParam);
                reportError(unErrorString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
