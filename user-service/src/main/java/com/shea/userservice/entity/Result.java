package com.shea.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/19 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<T>("200","操作成功",null);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>("200","操作成功",data);
    }

    public static <T> Result<T> error(String code,String message){
        return new Result<T>(code,message,null);
    }

    public static <T> Result<T> error(String message){
        return new Result<T>("500",message,null);
    }
}
