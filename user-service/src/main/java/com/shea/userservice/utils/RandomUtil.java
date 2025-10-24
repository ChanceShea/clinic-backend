package com.shea.userservice.utils;

import java.util.UUID;

/**
 * @description: 随机数生成器
 * @Author: Shea.
 * @Date: 2025/10/19 21:09
 */
public class RandomUtil {


    public static String number(Integer length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }

    public static String simpleUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String UUID(){
        return UUID.randomUUID().toString();
    }
}
