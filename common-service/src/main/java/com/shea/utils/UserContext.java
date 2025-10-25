package com.shea.utils;

import java.util.Map;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/20 14:42
 */
public class UserContext {

    private static final ThreadLocal<Map<String, Object>> tl = new ThreadLocal<>();

    public static void setClaims(Map<String, Object> claims) {
        UserContext.tl.set(claims);
    }

    public static Map<String, Object> getClaims() {
        return tl.get();
    }

    public static void clear() {
        tl.remove();
    }
}
