package com.cui.redis.rediscache.utils;

import lombok.Getter;

/**
 * @Auther: cui
 * @Date: 2019/1/16 12:02
 * @Description:
 */
@Getter
public enum RedisKeyUtils {

    USER_KEY("users");

    private String msg;

    RedisKeyUtils(String msg) {
        this.msg = msg;
    }
}
