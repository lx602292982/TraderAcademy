package com.traderacademy.supprot.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：lixiang on 2016/8/27 12:53
 * 邮箱：xiang.li@spreadwin.com
 */
public class ToastUtils {

    public static void setToastContext(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
