package com.traderacademy.http.modelImpl;

/**
 * create by Eric
 * create date 2016/05/10
 */
public interface ResultParamImpl {
    public Object getData();
    public boolean isStatus();
    public boolean isUnLoginStatus();
    public String getErrorsStr();
    public String getMessage();
}
