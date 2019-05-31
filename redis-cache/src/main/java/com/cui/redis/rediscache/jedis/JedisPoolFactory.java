package com.cui.redis.rediscache.jedis;

import com.cui.redis.rediscache.utils.OSinfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: cui
 * @Date: 2019/1/17 10:09
 * @Description:
 */
@Slf4j
@Configuration
public class JedisPoolFactory {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.block-when-exhausted}")
    private boolean blockWhenExhausted;

    @Value("${spring.redis.sentinel.nodes}")
    private String redisSentinelNodes;

    @Value("${spring.redis.sentinel.master}")
    private String redisSentinelMasterName;

    @Value("${spring.redis.sentinel.pool.max-total}")
    private int redisSentinelMaxTotal;
    @Value("${spring.redis.sentinel.pool.max-idle}")
    private int redisSentinelMaxIdle;
    @Value("${spring.redis.sentinel.pool.min-idle}")
    private int redisSentinelMinIdle;

    @Bean
    public JedisPool redisPoolFactory() throws Exception {
        /*log.info("JedisPool注入成功！！");
        log.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool;
        if ("Windows".equals(OSinfo.getOSname().toString())) {
            log.info("Windows系统");
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        } else {
            host = "r-m5e46d33484f2124.redis.rds.aliyuncs.com";
            password = "ASDasd19981018";
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }*/
        return new JedisPool();
    }

    /**
     *@Description  master-salve，哨兵
     *@Params
     *@Return
     *@Author  cui
     *@Date  2019/5/28
     */
    @Bean
    public JedisSentinelPool redisSentinelsPoolFactory() throws Exception {
        log.info("JedisPool注入成功！！");
        log.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        String[] hosts = redisSentinelNodes.split(",");
        Set<String> sentinels = new HashSet<>(Arrays.asList(hosts));
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(redisSentinelMaxTotal);
        poolConfig.setMaxIdle(redisSentinelMaxIdle);
        poolConfig.setMinIdle(redisSentinelMinIdle);
        JedisSentinelPool jedisSentinelPool;
        if ("Windows".equals(OSinfo.getOSname().toString())) {
            log.info("Windows系统");
            jedisSentinelPool = new JedisSentinelPool(redisSentinelMasterName, sentinels, jedisPoolConfig);
        } else {
            host = "r-m5e46d33484f2124.redis.rds.aliyuncs.com";
            password = "ASDasd19981018";
            jedisSentinelPool = new JedisSentinelPool(redisSentinelMasterName, sentinels, jedisPoolConfig);
        }
        return jedisSentinelPool;
    }

    /**
     *@Description  redis集群
     *@Params
     *@Return
     *@Author  cui
     *@Date  2019/5/28
     */
    /*@Bean
    public JedisCluster getJedisCluster() throws Exception {
        log.info("JedisCluster！！");
        log.info("redis地址：" + clusterNodes);
        String[] cNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        //分割出集群节点
        for (String node : cNodes) {
            String[] item = node.split(":");
            String ip = item[0];
            int port = Integer.valueOf(item[1]);
            nodes.add(new HostAndPort(ip, port));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisCluster jedisCluster = null;
        if ("Windows".equals(OSinfo.getOSname().toString())) {
            log.info("Windows系统");
            jedisCluster = new JedisCluster(nodes, timeout, jedisPoolConfig);
        } else {
            host = "r-m5e46d33484f2124.redis.rds.aliyuncs.com";
            password = "ASDasd19981018";
            jedisCluster = new JedisCluster(nodes, timeout, jedisPoolConfig);
        }
        return jedisCluster;
    }*/
}
