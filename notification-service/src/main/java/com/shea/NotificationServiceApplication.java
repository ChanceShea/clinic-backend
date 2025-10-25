package com.shea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/24 20:07
 */
@SpringBootApplication
@MapperScan("com.shea.notificationservice.mapper")
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
