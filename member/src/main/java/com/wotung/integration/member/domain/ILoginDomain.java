package com.wotung.integration.member.domain;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import com.wotung.integration.member.entity.Member;
import org.springframework.web.bind.annotation.RequestParam;

public interface ILoginDomain {
    Boolean register(String phone);
    Boolean setPasswd(String phone, String validateNo, String passwd);
    Boolean resetPasswd(String phone, String oldPasswd, String newPasswd);
    Boolean passwdLogin(String phone, String passwd);
    Boolean updateMember(Member member);
}
