package com.springboot.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class AbstractRedis implements IRedis {

	@Autowired
	protected StringRedisTemplate stringRedisTemplate;

}
