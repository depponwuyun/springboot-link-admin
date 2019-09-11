package com.springboot.core.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface IRedis {
	void set(String key, String value);

	void set(String key, String value, long timeout);

	void set(String key, String value, long timeout, TimeUnit unit);

	String get(String key);

	void hashSet(String key, Object hashKey, Object value);

	void hashSetAll(String key, Map<? extends Object, ? extends Object> m);

	Object hashGet(String key, Object hashKey);

	Map<Object, Object> hashGetAll(String key);

	void delete(String key);

	Boolean hasKey(String key);

	void expire(String key, long timeout);

	void expire(String key, long timeout, TimeUnit unit);

}
