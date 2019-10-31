package com.wotung.integration.member;

import com.wotung.integration.member.uitl.JWTHelper;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.security.Key;

public class JWTHelperTest {
    @Test
    public void createJWT(){

        String jwt = JWTHelper.createJWT("1", "111", "admin", 120*1000L);
        System.out.println(jwt);
    }

    @Test
    public void parseJWT(){
        Claims claims = JWTHelper.parseJWT("eyJhbGciOiJIUzI1NiJ9" +
                ".eyJqdGkiOiIxIiwiaWF0IjoxNTcyNTEzMjgxLCJzdWIiOiJhZG1pbiIsImlzcyI6IjExMSIsImV4cCI6MTU3MjUxMzQwMX0" +
                ".z6boNxyxIAl7rByIi2Vukc64gLc3V9Bg7Q55LZ9OQfg");
        System.out.println(claims);
    }

}
