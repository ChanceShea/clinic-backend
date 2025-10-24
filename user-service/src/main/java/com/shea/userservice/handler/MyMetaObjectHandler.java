package com.shea.userservice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description: MP自动填充字段处理器
 * @Author: Shea.
 * @Date: 2025/10/19 20:28
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert fill...");
        strictInsertFill(metaObject,"createTime",LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject,"updateTime",LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update fill...");
        strictUpdateFill(metaObject,"updateTime",LocalDateTime.class, LocalDateTime.now());
    }
}
