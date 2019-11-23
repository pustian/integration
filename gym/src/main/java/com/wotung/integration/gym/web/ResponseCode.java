package com.wotung.integration.gym.web;

public enum ResponseCode {
    OK          ("200", "OK"),
    SYSTEM_ERROR("999999", "System error"),
    TOKEN_ERROR ("990001", "TOKEN ERROR"),
    // 注册
    ADD_ERROR("001001001", "场馆已经添加"),

    // 登录
    LOGIN_FAILED("002001001", "用户或密码错误"),

    ;

    public String code;
    public String message;

    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getByCode(String code) {
        for(ResponseCode responseCode: values() ) {
            if(responseCode.code.equals(code)) {
                return responseCode;
            }
        }
        return ResponseCode.SYSTEM_ERROR;
    }
}
