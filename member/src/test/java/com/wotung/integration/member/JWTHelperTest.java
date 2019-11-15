package com.wotung.integration.member;

import com.wotung.integration.member.uitl.JWTHelper;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.security.Key;

public class JWTHelperTest {
    @Test
    public void createJWT(){
        String jwt = JWTHelper.createJWT("123", "田");
        System.out.println(jwt);
    }

    @Test
    public void parseJWT(){
        Claims claims = JWTHelper.parseJWT("eyJhbGciOiJIUzI1NiJ9" +
                ".eyJwaG9uZSI6IjEyMyIsIm5hbWUiOiLnlLAiLCJleHAiOjE1NzMyNDAyMDF9" +
                ".e95XyGdVUypgs0ii13q95akAWKJFm5aoVUVA7Xnhg6I");
        System.out.println(claims);
    }

}
