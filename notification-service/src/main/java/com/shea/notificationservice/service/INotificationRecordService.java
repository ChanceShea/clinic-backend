package com.shea.notificationservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shea.notificationservice.entity.dto.NotificationRecordPageDTO;
import com.shea.notificationservice.entity.pojo.NotificationRecord;
import com.shea.notificationservice.entity.vo.NotificationRecordVO;

/**
 * <p>
 * 通知记录表 服务类
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
public interface INotificationRecordService extends IService<NotificationRecord> {

    Page<NotificationRecordVO> queryRecordByPage(NotificationRecordPageDTO param);
}
