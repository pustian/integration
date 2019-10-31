/**
 * Copyright (C), 2016-2017, 上海友米互联网科技有限公司
 */
package com.wotung.integration.member.web.vo.header;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RespHeader implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "对应请求的唯一流水号")
	private String reqId;
	@ApiModelProperty(value = "对应请求的会话场景ID")
	private String sessionId;
	@ApiModelProperty(value = "响应码", required = true)
	private String respCode;
	@ApiModelProperty(value = "响应描述", required = true)
	private String respMessage;
	@ApiModelProperty(value = "响应更新token")
	private String token;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	@Override
	public String toString() {
		return "RespHeader{" +
				"reqId='" + reqId + '\'' +
				", sessionId='" + sessionId + '\'' +
				", respCode='" + respCode + '\'' +
				", respMessage='" + respMessage + '\'' +
				", token='" + token + '\'' +
				'}';
	}
}
