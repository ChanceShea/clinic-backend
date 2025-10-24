package com.shea.userservice.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.shea.userservice.annotation.Sensitive;
import com.shea.userservice.enums.DataEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @Sensitive(value = DataEnum.PASSWORD)
    private String password;

    /**
     * 个人邮箱
     */
    private String email;

    /**
     * 用户类型：0：管理员 1：患者 2：医生
     */
    private Integer userType;

    /**
     * 状态：0：锁定  1：正常
     */
    private Integer status;

    /**
     * 删除标记：0：否 1：是
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
