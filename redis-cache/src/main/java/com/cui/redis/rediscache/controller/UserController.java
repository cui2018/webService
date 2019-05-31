package com.cui.redis.rediscache.controller;

import com.cui.mq.rabbitMq.MqSender;
import com.cui.redis.rediscache.asyncpay.AsyncPaymentService;
import com.cui.redis.rediscache.asyncpay.PaymentRequset;
import com.cui.redis.rediscache.asyncpay.PaymentSubmitExecutor;
import com.cui.redis.rediscache.entity.User;
import com.cui.redis.rediscache.redistemplate.service.RedisService;
import com.cui.redis.rediscache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: cui
 * @Date: 2019/1/16 09:53
 * @Description:
 */
@RestController
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    RedisService redisService;
    @Autowired
    private static RedisProperties properties;

    @Autowired
    private Registration registration;

    @Autowired
    MqSender mqSender;



    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    AsyncPaymentService asyncPaymentService;
    @Autowired
    PaymentSubmitExecutor paymentSubmitExecutor;
    //@Cacheable(value = "users", key = "#userId")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Object getUser(@PathVariable Long userId) {
        log.info("获取user信息根据ID-> {}.", userId);
        /*User userRedis = redisService.getUserRedis(userId + "");
        if (userRedis == null) {
            userRedis = userRepository.findById(userId).get();
            redisService.saveUserRedis(userId + "", userRedis);
        }
        return userRedis;*/
        for(int i = 0; i<4; i++){
            mqSender.sendMsg(String.valueOf(i));
        }
        PaymentRequset paymentRequset = new PaymentRequset();
        paymentRequset.setTotalFee("10000");
        String s = asyncPaymentService.submit(paymentRequset);
        return s;
    }
    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id){
        paymentSubmitExecutor.toString();
        User user = this.userRepository.findById(id).get();
        return user;
    }
    @GetMapping("/getIpAndPort")
    public String getIdAndPort(){
        return this.registration.getHost()+":"+this.registration.getPort();
    }

}
