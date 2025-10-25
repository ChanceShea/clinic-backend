package com.shea.notificationservice.entity.dto;

import com.shea.notificationservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/25 14:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplatePageDTO {

    private String templateName;

    private String templateType;

    private Status status;

    private String startCreateTime;

    private String endCreateTime;

    private String startUpdateTime;

    private String endUpdateTime;

    private int pageNum;

    private int pageSize;

}
