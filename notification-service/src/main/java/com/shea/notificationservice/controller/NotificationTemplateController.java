package com.shea.notificationservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shea.Result;
import com.shea.notificationservice.entity.dto.NotificationTemplateAddDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplatePageDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplateUpdateDTO;
import com.shea.notificationservice.entity.vo.NotificationTemplateVO;
import com.shea.notificationservice.service.INotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 消息模板表 前端控制器
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@RestController
@RequestMapping("/notification-template")
@RequiredArgsConstructor
public class NotificationTemplateController {

    private final INotificationTemplateService notificationTemplateService;

    @GetMapping("/queryTemplateByPage")
    public Result<Page<NotificationTemplateVO>> queryTemplateByPage(@ModelAttribute  NotificationTemplatePageDTO param){
        return Result.success(notificationTemplateService.queryTemplateByPage(param));
    }

    @PostMapping("/addNotificationTemplate")
    public Result addNotificationTemplate(@RequestBody NotificationTemplateAddDTO param){
        notificationTemplateService.addNotificationTemplate(param);
        return Result.success();
    }

    @DeleteMapping("/removeNotificationTemplate")
    public Result removeNotificationTemplate(@RequestParam Long id){
        notificationTemplateService.removeNotificationTemplate(id);
        return Result.success();
    }

    @PutMapping("/updateNotificationTemplate")
    public Result updateNotificationTemplate(@RequestBody NotificationTemplateUpdateDTO param){
        notificationTemplateService.updateNotificationTemplate(param);
        return Result.success();
    }
}
