package com.cui.redis.rediscache.asyncpay;

import com.alibaba.fastjson.JSONObject;
import com.cui.redis.rediscache.jedis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: cui
 * @Date: 2019/2/10 18:29
 * @Description:
 */
@Slf4j
@Service
public class AsyncPaymentService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PaymentSubmitExecutor paymentSubmitExecutor;
    //添加支付请求
    public String submit(PaymentRequset paymentRequset) {
        log.info("校验请求参数：{}", paymentRequset);
        //添加支付请求
        redisUtil.lpush(0, "pay_queue", JSONObject.toJSONString(paymentRequset));
        String cuicuic = redisUtil.get("cuicuic", 0);
        for(int i = 21; i<25; i++){
            boolean cuic = redisUtil.setbit("cuic", (long) i, "1");
            System.out.println(cuic);
        }
        int index = redisUtil.bitCountByBitIndex("cuic", (long) 0, (long) 2);
        System.out.println(index);
        System.out.println(redisUtil.getbit("cuic", (long)1));
        System.out.println(redisUtil.getbit("cuic", (long)2));
        log.info("读数据：{}", cuicuic);
         //redissonClient.getQueue("pay_queue").add(paymentRequset);
        return "success";
    }
    //模拟第三方支付
    public String doPay(PaymentRequset paymentRequset) {
        log.info("第三方开始处理支付请求：{}", paymentRequset);
        redisUtil.lpush(0,"pay_result_notify", JSONObject.toJSONString(paymentRequset));
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "success";
    }
}
