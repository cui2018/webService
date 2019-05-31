package com.cui.redis.rediscache.repository;

import com.cui.redis.rediscache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cui
 * @Date: 2019/1/16 09:48
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
