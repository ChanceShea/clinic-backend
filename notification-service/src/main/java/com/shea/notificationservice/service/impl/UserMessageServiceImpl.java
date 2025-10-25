package com.shea.notificationservice.service.impl;

import com.shea.notificationservice.mapper.UserMessageMapper;
import com.shea.notificationservice.entity.pojo.UserMessage;
import com.shea.notificationservice.service.IUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户站内消息表 服务实现类
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements IUserMessageService {

}
