package com.wotung.integration.member.uitl;


import com.wotung.integration.member.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JWTHelper {


    //Sample method to construct a JWT
//    public static String createJWT(String phone) {
//        return createJWT(Constants.SERVICE_NAME, null, phone, null, Constants.EXP_TTL_Millis);
//    }
    public static String createJWT(String phone, String name) {
        return createJWT(Constants.SERVICE_NAME, null, phone, name, Constants.EXP_TTL_Millis);
    }

    public static String createJWT(String issuer, String subject, String phone, String name, long ttlMillis) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary( Constants.APP_ID + Constants.APP_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

//        //Let's set the JWT Claims
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("phone", phone);
        claims.put("name", name);
        //        iss: jwt签发者  // 签发模块
        //        sub: jwt所面向的用户 // phone
        //        aud: 接收jwt的一方
        //        exp: jwt的过期时间，这个过期时间必须要大于签发时间
        //        nbf: 定义在什么时间之前，该jwt都是不可用的.
        //                iat: jwt的签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder() // .setId(id)
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    //Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constants.APP_ID + Constants.APP_SECRET) )
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
