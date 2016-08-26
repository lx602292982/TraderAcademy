package com.traderacademy.http.model;

import java.io.Serializable;
import java.util.List;

import com.traderacademy.http.modelImpl.ResultParamImpl;

/**
 * 服务器相应数据，多处会用到
 * create by Eric
 * create date 2016/05/10
 */

public class ResultParam implements Serializable, ResultParamImpl {
    private String status;             // 状态
    private List<String> errors;         // 错误信息
    private String message;

    public String getStatus() {
        return status;
    }

    @Override
    public boolean isStatus() {
        return "200".equals(status);
    }

    @Override
    public boolean isUnLoginStatus() {
        return "209".equals(status);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage(){return message;}

    public void setMessage(String message){this.message = message;}

    public String toString() {
        if (errors != null)
            return "status: " + Boolean.toString("200".equals(status)) + "\n" + errors.toString();

        return "status: " + Boolean.toString("200".equals(status));
    }

    @Override
    public String getErrorsStr() {
        String s="";
        if (errors != null) {
            for (String str:errors )
                s +=str + "\n";

            s = s.substring(0, s.length() - 1);
            return s;
        }
        return "未知错误";
    }

    @Override
    public Object getData() {
        return null;
    }
}
