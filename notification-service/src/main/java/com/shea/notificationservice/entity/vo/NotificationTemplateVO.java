package com.shea.notificationservice.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/25 14:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplateVO {

    private Long id;

    private String templateCode;

    private String templateName;

    private String templateType;

    private String titleTemplate;

    private String contentTemplate;

    private String status;

    private String description;

    private String createTime;

    private String updateTime;
}
