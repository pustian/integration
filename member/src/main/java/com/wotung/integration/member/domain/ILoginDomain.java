package com.wotung.integration.member.domain;

import com.wotung.integration.member.entity.Member;

public interface ILoginDomain {
    Boolean register(String phone, String name);
    Boolean setPasswd(String phone, String validateNo, String passwd);
    Boolean resetPasswd(String phone, String oldPasswd, String newPasswd);
    Boolean passwdLogin(String phone, String passwd);
    Boolean updateMember(Member member);
}
