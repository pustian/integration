package com.wotung.integration.gym.web.vo.gym;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wotung.integration.gym.web.vo.entity.ReqEntity;

public class GymReq implements  ReqEntity {


    private Integer id;
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

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }



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
