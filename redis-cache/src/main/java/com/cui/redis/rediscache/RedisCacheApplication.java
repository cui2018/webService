package com.cui.redis.rediscache;

import com.cui.redis.rediscache.entity.User;
import com.cui.redis.rediscache.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cui"})
//开启缓存
@EnableCaching
@EnableEurekaClient
public class RedisCacheApplication implements CommandLineRunner {
	private final UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public RedisCacheApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("开始初始化user ->user count ->{}",userRepository.count());
		User james = new User("James", 2000);
		User potter = new User("Potter", 4000);
		User dumbledore = new User("Dumbledore", 999999);

		userRepository.save(james);
		userRepository.save(potter);
		userRepository.save(dumbledore);
		logger.info("初始化完成 数据-> {}.", userRepository.findAll());
	}
}

