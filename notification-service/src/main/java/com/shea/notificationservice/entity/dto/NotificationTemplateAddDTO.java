package com.shea.notificationservice.entity.dto;

import com.shea.notificationservice.enums.Status;
import com.shea.notificationservice.enums.TemplateType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/25 15:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplateAddDTO {

    @NotNull(message = "模板代码不能为空")
    private String templateCode;

    @NotNull(message = "模板名称不能为空")
    private String templateName;

    @NotNull(message = "模板类型不能为空")
    private TemplateType templateType;

    private String titleTemplate;

    @NotNull(message = "内容模板不能为空")
    private String contentTemplate;

    private Status status;

    private String description;

    private String createTime;

    private String updateTime;
}
