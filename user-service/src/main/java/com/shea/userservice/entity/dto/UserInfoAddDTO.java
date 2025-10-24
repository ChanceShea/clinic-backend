package com.shea.userservice.entity.dto;

import lombok.Data;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/19 20:15
 */
@Data
public class UserInfoAddDTO {
    
    private String password;

    private String realName;

    private String phone;

    private String idCard;

    private String birthDate;

    private String avatar;

    private String province;

    private String city;

    private String district;

}
