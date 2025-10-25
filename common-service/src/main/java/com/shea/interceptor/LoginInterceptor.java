package com.shea.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import com.shea.utils.JwtUtil;
import com.shea.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/20 14:23
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token == null || token.trim().isEmpty()){
            throw new RuntimeException("请先登录");
        }
        try{
            JWT jwt = JwtUtil.parseJwt(token);
            JWTPayload payload = jwt.getPayload();
            String username = (String) payload.getClaim("username");
            String auth = (String) payload.getClaim("auth");
            Map<String,Object> claims = Map.of("username",username,"auth",auth);
            UserContext.setClaims(claims);
        }catch (Exception e){
            throw new RuntimeException("请先登录");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }
}
