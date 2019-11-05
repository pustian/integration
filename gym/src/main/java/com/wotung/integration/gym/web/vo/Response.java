package com.wotung.integration.gym.web.vo;


import com.wotung.integration.gym.web.vo.entity.RespEntity;
import com.wotung.integration.gym.web.vo.header.RespHeader;

/**
 * Created by tianpusen on 2017/7/15.
 */
public class Response<T extends RespEntity> {
    RespHeader respHeader;
    T respBody;

    public RespHeader getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(RespHeader respHeader) {
        this.respHeader = respHeader;
    }

    public T getRespBody() {
        return respBody;
    }

    public void setRespBody(T respBody) {
        this.respBody = respBody;
    }

    @Override
    public String toString() {
        return "Response{" +
                "respHeader=" + respHeader +
                ", respBody=" + respBody +
                '}';
    }
}
