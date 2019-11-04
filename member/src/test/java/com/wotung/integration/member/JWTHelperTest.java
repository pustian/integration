package com.wotung.integration.member;

import com.wotung.integration.member.uitl.JWTHelper;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.security.Key;

public class JWTHelperTest {
    @Test
    public void createJWT(){
        String jwt = JWTHelper.createJWT("1", null);
        System.out.println(jwt);
    }

    @Test
    public void parseJWT(){
        Claims claims = JWTHelper.parseJWT("eyJhbGciOiJIUzI1NiJ9" +
                ".eyJqdGkiOiIxIiwiaXNzIjoibWVtYmVyIiwiaWF0IjoxNTcyODMzNTc2LCJleHAiOjE1NzI4MzM2MzZ9" +
                ".KAyzFDrYZfVaaHihrK9UusistWEbLKua0H5ufl0jJiw");
        System.out.println(claims);
    }

}
