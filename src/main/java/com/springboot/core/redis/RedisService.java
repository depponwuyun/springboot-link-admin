package com.springboot.core.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class RedisService extends AbstractRedis {

	@Override
	public void set(String key, String value) {
		super.stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, String value, long timeout) {
		set(key, value, timeout, TimeUnit.SECONDS);
	}

	public void set(String key, String value, long timeout, TimeUnit unit) {
		super.stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	@Override
	public String get(String key) {
		return super.stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void hashSet(String key, Object hashKey, Object value) {
		stringRedisTemplate.opsForHash().put(key, hashKey, value);

	}

	@Override
	public void hashSetAll(String key, Map<? extends Object, ? extends Object> m) {
		stringRedisTemplate.opsForHash().putAll(key, m);
	}

	@Override
	public Object hashGet(String key, Object hashKey) {
		return stringRedisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public Map<Object, Object> hashGetAll(String key) {
		return stringRedisTemplate.opsForHash().entries(key);
	}

	@Override
	public void delete(String key) {
		super.stringRedisTemplate.delete(key);

	}

	@Override
	public Boolean hasKey(String key) {
		return super.stringRedisTemplate.hasKey(key);

	}

	@Override
	public void expire(String key, long timeout) {
		expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void expire(String key, long timeout, TimeUnit unit) {
		super.stringRedisTemplate.expire(key, timeout, unit);

	}

}
