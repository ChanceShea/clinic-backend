package com.shea.notificationservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/24 20:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRecordPageDTO {

    private String receiver;

    private String sender;

    private String notificationType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String businessType;

    private String businessId;

    private Integer page;

    private Integer pageSize;

}
