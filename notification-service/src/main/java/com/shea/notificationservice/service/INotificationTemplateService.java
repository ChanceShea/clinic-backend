package com.shea.notificationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shea.notificationservice.entity.dto.NotificationTemplateAddDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplatePageDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplateUpdateDTO;
import com.shea.notificationservice.entity.pojo.NotificationTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shea.notificationservice.entity.vo.NotificationTemplateVO;

/**
 * <p>
 * 消息模板表 服务类
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
public interface INotificationTemplateService extends IService<NotificationTemplate> {

    Page<NotificationTemplateVO> queryTemplateByPage(NotificationTemplatePageDTO param);

    void addNotificationTemplate(NotificationTemplateAddDTO param);

    void removeNotificationTemplate(Long id);

    void updateNotificationTemplate(NotificationTemplateUpdateDTO param);
}
