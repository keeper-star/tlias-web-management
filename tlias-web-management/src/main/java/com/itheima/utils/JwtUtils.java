package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "SVRIRUlNQQ=="; // 签名密钥
    private static Long expire = 43200000L;  // 过期时间，单位毫秒

    /**
     * 生成JWT令牌
     * @return JWT令牌
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)  // 添加负载
                .signWith(SignatureAlgorithm.HS256, signKey)  // 使用HS256算法和signKey进行签名
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)  // 使用signKey进行签名
                .parseClaimsJws(jwt)  // 解析JWT令牌
                .getBody();
        return claims;
    }
}