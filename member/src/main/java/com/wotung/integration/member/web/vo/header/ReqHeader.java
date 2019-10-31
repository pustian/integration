/**
 * Copyright (C), 2016-2017, 上海友米互联网科技有限公司
 */
package com.wotung.integration.member.web.vo.header;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public final class ReqHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "调用子公司ID")
    private String entityId;
    @ApiModelProperty(value = "接入方系统编号。新接入时，由平台分发")
    private String appId;
    @ApiModelProperty(value = "会话场景ID")
    private String sessionId;
    @ApiModelProperty(value = "请求的唯一流水号")
    private String reqId;

    @ApiModelProperty(value = "访问请求token")
    private String accessToken;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "ReqHeader{" +
                "entityId='" + entityId + '\'' +
                ", appId='" + appId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", reqId='" + reqId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

}
