package com.shea.notificationservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shea.Result;
import com.shea.notificationservice.entity.dto.NotificationRecordPageDTO;
import com.shea.notificationservice.entity.vo.NotificationRecordVO;
import com.shea.notificationservice.service.INotificationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通知记录表 前端控制器
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@RestController
@RequestMapping("/notification-record")
@RequiredArgsConstructor
public class NotificationRecordController {

    private final INotificationRecordService notificationRecordService;

    @GetMapping("/queryRecordByPage")
    public Result<Page<NotificationRecordVO>> queryRecordByPage(NotificationRecordPageDTO param){
        return Result.success(notificationRecordService.queryRecordByPage(param));
    }
}
