package com.shea.userservice.apsect;

import com.shea.userservice.annotation.Sensitive;
import com.shea.userservice.entity.Result;
import com.shea.userservice.enums.DataEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @description: 敏感字段加密切面
 * @Author: Shea.
 * @Date: 2025/10/19 16:54
 */
@Aspect
@Component
@Slf4j
public class EncryptAspect {

    @Around("@annotation(com.shea.userservice.annotation.EncryptField)")
    public Object encrypt(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        log.info("开始加密敏感字段");
        if(result == null){
            return null;
        }
        Result res = (Result)result;
        if(res.getData() == null){
            return result;
        }
        Object data = res.getData();
        Field[] declaredFields = data.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if(declaredField.isAnnotationPresent(Sensitive.class)){
                encryptField(declaredField,data);
            }
        }
        return result;
    }

    private void encryptField(Field field,Object obj) throws IllegalAccessException {
        DataEnum value = field.getAnnotation(Sensitive.class).value();
        switch (value){
            case NAME -> encryptName(field,obj);
            case PHONE -> encryptPhone(field,obj);
            case ID_CARD -> encryptIdCard(field,obj);
            case PASSWORD -> encryptPassword(field,obj);
        }
    }

    private void encryptPassword(Field field, Object obj) throws IllegalAccessException {
        String password = null;
        if(field.getType() == String.class){
            password = (String) field.get(obj);
        }
        password = password.replaceAll(password,"*");
        field.set(obj,password);
    }

    private void encryptIdCard(Field field, Object obj) throws IllegalAccessException {
        String idCard = null;
        if(field.getType() == String.class){
            idCard = (String) field.get(obj);
        }
        idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})","$1****$2");
        field.set(obj,idCard);
    }

    private void encryptName(Field field,Object obj) throws IllegalAccessException {
        String name = null;
        if(field.getType() == String.class){
            name = (String) field.get(obj);
        }
        if(name.length() == 2){
            name = name.replace(name.charAt(1),'*');
        }else if(name.length() > 2){
            name = name.charAt(0) + "*" + name.charAt(name.length()-1);
        }
        field.set(obj,name);
    }

    private void encryptPhone(Field field,Object obj) throws IllegalAccessException {
        String phone = null;
        if(field.getType() == String.class){
            phone = (String) field.get(obj);
        }
        phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        field.set(obj,phone);
    }
}
