package com.cui.redis.rediscache.asyncpay;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;

/**
 * @Auther: cui
 * @Date: 2019/2/10 17:53
 * @Description:
 */
public class RedisUtils {
    private volatile static RedisUtils redisUtils;
    private RedisUtils(){}
    public static RedisUtils getIntstance(){
        if(redisUtils == null){
            synchronized (RedisUtils.class){
                if(redisUtils == null){
                    redisUtils = new RedisUtils();
                }
            }
        }
        return redisUtils;
    }

    public RedissonClient getRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return  redissonClient;
    }
}
