package com.shea.handler;

import com.shea.exception.ClientException;
import com.shea.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * @description: 全局异常处理器
 * @Author: Shea.
 * @Date: 2025/10/24 15:14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 新增：处理 LoginFailedException
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailedException(
            ClientException ex) {

        log.error("客户端异常: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),       // 使用异常中的错误码
                ex.getMessage(),    // 使用异常中的消息
                LocalDateTime.now()
        );
        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR  // 401 状态码
        );
    }
}
