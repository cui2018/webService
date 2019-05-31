package com.cui.redis.rediscache.redistemplate.service;

import com.cui.redis.rediscache.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: cui
 * @Date: 2019/1/16 11:56
 * @Description:
 */
@Service
public interface RedisService {
    void saveUserRedis(String userId, User userJson);
    User getUserRedis(String userId);
    void unlikeFromRedis(String likedUserId, String likedPostId);
    void deleteLikedFromRedis(String likedUserId, String likedPostId);
    void incrementLikedCount(String likedUserId);
    void decrementLikedCount(String likedUserId);
    List<User> getLikedDataFromRedis();
}
