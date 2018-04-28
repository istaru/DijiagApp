package com.hb.zll.dijiag.request;

/**
 * Created by Moon on 2018/4/19.
 */

public class ApiException extends Exception{

    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
