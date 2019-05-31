package com.cui.redis.rediscache.redistemplate.service.impl;

import com.cui.redis.rediscache.entity.User;
import com.cui.redis.rediscache.jedis.RedisUtil;
import com.cui.redis.rediscache.redistemplate.service.RedisService;
import com.cui.redis.rediscache.utils.RedisKeyUtils;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Auther: cui
 * @Date: 2019/1/16 11:56
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void saveUserRedis(String userId, User userJson) {
        redisUtil.setSerializable(userId, userJson, 1);
        redisTemplate.opsForHash().put(RedisKeyUtils.USER_KEY.getMsg(), userId, userJson);
    }

    @Override
    public User getUserRedis(String userId) {
        User serializable = (User) redisUtil.getSerializable(userId, 1);
        User user = (User) redisTemplate.opsForHash().get(RedisKeyUtils.USER_KEY.getMsg(), userId);
        return user;
    }

    @Override
    public void unlikeFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void incrementLikedCount(String likedUserId) {

    }

    @Override
    public void decrementLikedCount(String likedUserId) {

    }

    @Override
    public List<User> getLikedDataFromRedis() {
        return null;
    }
}
