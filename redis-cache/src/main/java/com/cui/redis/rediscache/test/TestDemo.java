package com.cui.redis.rediscache.test;

import com.cui.redis.config.ConfigEntity;
import com.cui.redis.rediscache.asyncpay.PaymentSubmitExecutor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: cui
 * @Date: 2019/2/16 21:57
 * @Description:
 */
public class TestDemo {
    public static void main(String[] args) {
        //bean装配
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigEntity.class);
        context.close();
    }
}
