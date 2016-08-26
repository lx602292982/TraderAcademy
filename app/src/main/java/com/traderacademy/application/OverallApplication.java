package com.traderacademy.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.traderacademy.R;
import com.traderacademy.domain.LoginParam;
import com.traderacademy.domain.UserData;

/**
 * Created by Eric
 * on 2015/12/20.
 * 取代程序本身的application，处理一系列问题
 */
public class OverallApplication extends Application {
    private static Context context;
    //保存与服务器对接的sessionid
    public static String cookie;

    //用户数据
    public static UserData userData;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //initModels();
        //System.out.println("全局application生效");
        //这里是程序崩溃时的处理方法，保存报错信息，
        /*CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
    }

    @Override
    public void onTerminate() {
        Log.d("App", "onTerminate");

        super.onTerminate();
    }

    public static LoginParam getLoginParam() {
        SharedPreferences read = getSharedPreFrences();
        if (read != null) {
            LoginParam param = new LoginParam();
            String password = read.getString("u_password", "");
            String username = read.getString("u_username", "");
            param.setUsername(username);
            param.setPassword(password);
            return param;
        } else {
            return null;
        }
    }

    public static SharedPreferences getSharedPreFrences() {
        SharedPreferences read = context.getSharedPreferences(context.getString(R.string.app_shared_preferences), Activity.MODE_WORLD_READABLE);
        return read;
    }

}