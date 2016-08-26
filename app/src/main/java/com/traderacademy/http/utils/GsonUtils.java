package com.traderacademy.http.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Admin on 2016/1/27.
 */
public class GsonUtils {
    public static <Entity> Entity asEntity(String json, Type typeOfT) {
        try {
            Log.d("NetResult", json);
            Gson gson = new Gson();
            return gson.fromJson(json, typeOfT);
        } catch (Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
}
