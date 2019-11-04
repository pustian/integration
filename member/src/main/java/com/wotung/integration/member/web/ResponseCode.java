package com.wotung.integration.member.web;

public enum ResponseCode {
    OK          ("000000", "OK"),
    SYSTEM_ERROR("999999", "System error"),
    TOKEN_ERROR ("990001", "TOKEN ERROR"),
    ;

    public String code;
    public String message;

    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
