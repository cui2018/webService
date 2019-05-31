package com.cui.redis.config;

import com.cui.redis.rediscache.asyncpay.PaymentSubmitExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: cui
 * @Date: 2019/2/17 15:42
 * @Description:
 */
@Configuration
public class ConfigEntity {
    /*@Bean(initMethod = "init")
    public PaymentSubmitExecutor getPaymentSubmitExecutor(){
        return new PaymentSubmitExecutor();
    }*/
}
