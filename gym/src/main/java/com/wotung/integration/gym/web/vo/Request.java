package com.wotung.integration.gym.web.vo;


import com.wotung.integration.gym.web.vo.entity.ReqEntity;
import com.wotung.integration.gym.web.vo.header.ReqHeader;

import javax.validation.constraints.NotNull;

/**
 * Created by tianpusen on 2017/7/15.
 */
public class Request<T extends ReqEntity> {
    @NotNull
    ReqHeader reqHeader;
    T reqBody;

    public ReqHeader getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(ReqHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    public T getReqBody() {
        return reqBody;
    }

    public void setReqBody(T reqBody) {
        this.reqBody = reqBody;
    }

    @Override
    public String toString() {
        return "Request{" +
                "reqHeader=" + reqHeader +
                ", reqBody=" + reqBody +
                '}';
    }
}
