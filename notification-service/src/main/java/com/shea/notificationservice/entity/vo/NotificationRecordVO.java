package com.shea.notificationservice.entity.vo;

import com.shea.notificationservice.enums.Status;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/24 20:52
 */
public class NotificationRecordVO {

    private String messageId;

    private String notificationType;

    private String sender;

    private String receiver;

    private String title;

    private String content;

    private String variables;

    private Status status;

    private String errorMessage;

    private String businessType;

    private String businessId;

    private String createdTime;

    private String updatedTime;

    private String scheduledTime;

    private String sentTime;
}
