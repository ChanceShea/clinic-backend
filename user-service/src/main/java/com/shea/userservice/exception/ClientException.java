package com.shea.userservice.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{
    private final int code;
    private final String message;

    public ClientException(int code, String message) {
        super(message);  // 调用父类构造方法传递消息
        this.code = code;
        this.message = message;
    }

    public ClientException(String message){
        super(message);
        this.message = message;
        this.code = 500;
    }
}
