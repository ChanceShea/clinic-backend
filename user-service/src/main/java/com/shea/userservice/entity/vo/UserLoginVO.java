package com.shea.userservice.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/20 14:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {

    private String token;
    private UserInfoVO userInfo;
}
