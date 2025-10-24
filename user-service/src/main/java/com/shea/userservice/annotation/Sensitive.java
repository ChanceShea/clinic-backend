package com.shea.userservice.annotation;

import com.shea.userservice.enums.DataEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/18 23:42
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensitive {
    DataEnum value();
}
