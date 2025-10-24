package com.shea.userservice.utils;

import cn.hutool.jwt.JWT;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.Date;
import java.util.Map;


public class JwtUtil {

    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private static final String SECRET_KEY = "zxbnm@1&*74";
    public static String createJwt(Map<String,Object> claims){
        return createJwt(claims, new Date(System.currentTimeMillis() + EXPIRATION_TIME));
    }

    public static String createJwt(Map<String,Object> claims, Date expiration) {
        return JWT.create()
                .addPayloads(claims)  // 添加自定义claims
                .setExpiresAt(expiration)  // 设置过期时间
                .setKey(SECRET_KEY.getBytes())  // 设置密钥
                .sign();
    }

    public static JWT parseJwt(String jwt) {
        try {
            return JWT.create().parse(jwt);
        } catch (ExpiredJwtException e) {
            System.out.println("JWT已过期: " + e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            System.out.println("JWT格式错误: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("JWT解析失败: " + e.getMessage());
            throw e;
        }
    }
}
