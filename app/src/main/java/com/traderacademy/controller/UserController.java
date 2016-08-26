package com.traderacademy.controller;

import java.util.Observer;

import com.traderacademy.domain.LoginParam;
import com.traderacademy.domain.UserData;
import com.traderacademy.http.RequesterBase;
import com.traderacademy.http.services.RequestService;

/**
 * Created by Eric
 * on 2016/5/10
 * for project TRC
 */
public class UserController extends HttpController {

    /**
     * 登录的方法
     *
     * @param lp
     * @param observer
     */
    public static void loginToMain(LoginParam lp, Observer observer) {
        //调用中间层，进行数据请求
        RequestService.sendRequest(POST, RequesterBase.API_Login, observer, lp, UserData.class);
    }

    /**
     * 上传头像图片
     *
     * @param imgs
     * @param observer
     */
    /*public static void uploadImgs(UserRequestParam param, final Map<String, File> imgs, final Observer observer) {
        MultipartRequestParams params = new MultipartRequestParams();
        params.put("userId", param.getUserId() + "");
        for (Map.Entry<String, File> entry : imgs.entrySet()) {
            params.put("file", imgs.get(entry.getKey()));
        }
        RequestService.getQueue().add(new MultipartRequest(Request.Method.POST, params, RequesterBase.API_Upload_Head, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Tag", "response is " + response);
                UserResultParam result = GsonUtils.asEntity(response, UserResultParam.class);

                if (result.isStatus()) {
                    observer.update(null, result);
                } else {
                    reportError(result);
                    observer.update(null, null);
                }
                //TODO
            }
        }, new MyErrorListener(observer)));

    }*/

}
