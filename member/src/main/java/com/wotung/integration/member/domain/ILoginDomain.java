package com.wotung.integration.member.domain;

import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.web.ResponseCode;

public interface ILoginDomain {
//    Boolean register(String phone, String name);
    ResponseCode register2(String phone, String name, String email, String passwd);
    Boolean setPasswd(String phone, String validateNo, String passwd);
    Boolean resetPasswd(String phone, String oldPasswd, String newPasswd);
    ResponseCode passwdLogin(String phone, String passwd);
    ResponseCode updateMember(Member member);
}
