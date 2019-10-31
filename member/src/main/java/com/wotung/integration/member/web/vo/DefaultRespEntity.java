package com.wotung.integration.member.web.vo;


import com.wotung.integration.member.web.vo.entity.RespEntity;

/**
 * Created by tianpusen on 2017/9/3.
 */
public class DefaultRespEntity implements RespEntity {
    private static final long serialVersionUID = 1L;
    private String isSuccess;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "DefaultRespEntity{" +
                "isSuccess='" + isSuccess + '\'' +
                '}';
    }
}
