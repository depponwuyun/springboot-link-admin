package com.springboot.core.redis;

import org.springframework.stereotype.Component;

import com.springboot.core.web.SpringUtils;

@Component
public class RedisUtils {
	// cache
	private static volatile IRedis redis = null;

	public static IRedis getRedis() {
		if (redis == null) {
			redis =  SpringUtils.getBean(IRedis.class);;
		}
		return redis;
	}

}
