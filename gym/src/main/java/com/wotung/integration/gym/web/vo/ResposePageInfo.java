package com.wotung.integration.gym.web.vo;

import com.baomidou.mybatisplus.plugins.Page;

public class ResposePageInfo <T> extends Page {

    public String code;
    public String message;


    public ResposePageInfo() {
    }

    public ResposePageInfo(int current, int size) {
        super(current, size);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
