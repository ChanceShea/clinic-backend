package com.shea.userservice.entity.vo;

import com.shea.userservice.annotation.Sensitive;
import com.shea.userservice.enums.DataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/19 17:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    @Sensitive(DataEnum.NAME)
    private String realName;

    @Sensitive(DataEnum.PHONE)
    private String phone;

    private String email;

    private String address;

    private String avatar;

    private LocalDateTime birthDate;

    @Sensitive(DataEnum.ID_CARD)
    private String idCard;

}
