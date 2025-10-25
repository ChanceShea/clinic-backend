package com.shea.config;

import com.shea.utils.EmailUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @description: TODO
 * @Author: Shea.
 * @Date: 2025/10/24 21:57
 */

@Configuration
public class CommonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(EmailUtil.class)
    public EmailUtil emailUtil(StringRedisTemplate redisTemplate){
        return new EmailUtil(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(WebMvcConfig.class)
    public WebMvcConfig webMvcConfig(){
        return new WebMvcConfig();
    }

    @Bean
    @ConditionalOnMissingBean(MybatisPlusConfig.class)
    public MybatisPlusConfig mybatisPlusConfig(){
        return new MybatisPlusConfig();
    }
}
