package com.shea.userservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户注册参数
 * @Author: Shea.
 * @Date: 2025/10/23 16:42
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    private String password;

    private String email;

    private String code;
}
