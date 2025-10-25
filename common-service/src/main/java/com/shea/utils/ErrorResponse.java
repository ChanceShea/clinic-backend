package com.shea.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description: 错误信息响应
 * @Author: Shea.
 * @Date: 2025/10/24 15:16
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
    private LocalDateTime timestamp;
}
