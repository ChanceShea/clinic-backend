package com.shea.notificationservice.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 通知记录表
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("notification_record")
public class NotificationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息唯一ID
     */
    private String messageId;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 通知类型
     */
    private String notificationType;

    /**
     * 发送方标识
     */
    private String sender;

    /**
     * 接收方(邮箱/手机号/用户ID)
     */
    private String receiver;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 模板变量实际数据
     */
    private String variables;

    /**
     * 发送状态
     */
    private String status;

    /**
     * 重试次数
     */
    private Integer retryCount;

    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 计划发送时间
     */
    private LocalDateTime scheduledTime;

    /**
     * 实际发送时间
     */
    private LocalDateTime sentTime;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
