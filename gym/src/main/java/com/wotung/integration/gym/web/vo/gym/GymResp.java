package com.wotung.integration.gym.web.vo.gym;

import com.wotung.integration.gym.web.vo.entity.RespEntity;
public class GymResp implements RespEntity {

    /**
     * 场馆名字
     */
    private String name;
    /**
     * 场馆地址
     */
    private String address;
    /**
     * 联系方式
     */

    private String contactInfo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
