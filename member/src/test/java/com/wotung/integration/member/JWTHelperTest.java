package com.wotung.integration.member;

import com.wotung.integration.member.uitl.JWTHelper;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.security.Key;

public class JWTHelperTest {
    @Test
    public void createJWT(){
        String jwt = JWTHelper.createJWT("1", "1822");
        System.out.println(jwt);
    }

    @Test
    public void parseJWT(){
        Claims claims = JWTHelper.parseJWT("eyJhbGciOiJIUzI1NiJ9" +
                ".eyJqdGkiOiIxIiwic3ViIjoiMTgyMiIsImlzcyI6Im1lbWJlciIsImlhdCI6MTU3MjY2OTAzNSwiZXhwIjoxNTcyNjY5MDk1fQ" +
                ".FAShlDOGZQzWbYSp1EuSaRgDfyNrSHT0midTcx0ZO1Q");
        System.out.println(claims);
    }

}
