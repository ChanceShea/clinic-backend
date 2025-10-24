package com.shea.userservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.shea.userservice.constant.RedisConstants.EMAIL_CODE_KEY;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/24 14:39
 */
@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final StringRedisTemplate redisTemplate;

    public String generateEmailCode(String email){
        return RandomUtil.number(6);
    }

    public int verifyEmailCode(String email,String code){
        int result = 1;
        String key = EMAIL_CODE_KEY + email;
        String value = redisTemplate.opsForValue().get(key);
        if (value == null){
            result = 2;
        } else if (!code.equals(value)) {
            result = 0;
        }

        if (result == 1){
            redisTemplate.delete(key);
        }
        return result;
    }
}
