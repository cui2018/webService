package com.cui.redis.rediscache.asyncpay;

import com.alibaba.fastjson.JSONObject;
import com.cui.redis.rediscache.jedis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cui
 * @Date: 2019/2/10 18:04
 * @Description:
 */
@Slf4j
@Component
public class PaymentSubmitExecutor implements ApplicationRunner {
    //只有一个核心队列
    private final ExecutorService single = Executors.newSingleThreadExecutor();
    private volatile boolean isRunning = true;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    AsyncPaymentService asyncPaymentService;
    @Override
    public void run(ApplicationArguments var1) throws Exception {
        single.execute(() -> {
            while (isRunning) {
                PaymentRequset paymentRequset = unqueueTask();
                asyncPaymentService.doPay(paymentRequset);
            }
        });
    }
    public void init() {
        System.out.println("======init初始化开始======");
        /*single.execute(() -> {
            while (isRunning) {
                PaymentRequset paymentRequset = unqueueTask();
                asyncPaymentService.doPay(paymentRequset);
            }
        });*/
    }
    private PaymentRequset unqueueTask() {
        PaymentRequset paymentRequset;
        do {
            String pay_queue = redisUtil.rpop("pay_queue", 0);
            paymentRequset = JSONObject.parseObject(pay_queue, PaymentRequset.class);
            //paymentRequset = (PaymentRequset) redissonClient.getQueue("pay_queue").poll();
            if (paymentRequset == null) {
                try {
                    Thread.sleep(2000);
                    //log.debug("sleep 2 秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("redis queue poll：{}" , paymentRequset);
                break;
            }
        } while (paymentRequset == null && isRunning);
        return paymentRequset;
    }
}
