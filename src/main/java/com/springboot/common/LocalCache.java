package com.springboot.common;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存
 * 
 * @author Administrator
 *
 */
public class LocalCache {

	// 默认永久保存
	private static final long default_time_out = -1;
	// 存储容器
	private static Map<Object, LocalItem> cache = new ConcurrentHashMap<Object, LocalItem>();

	static {
		executeTaskProcess();
	}

	/**
	 * 向缓存中添加数据 ；默认永久存储在内存中，除非服务重启
	 *
	 * @param @param key
	 * @param @param value 设定文件
	 * @return void 返回类型
	 *
	 */
	public static void put(Object key, Object value) {
		put(key, value, default_time_out);
	}

	/**
	 * 向缓存中添加数据
	 *
	 * @param @param key
	 * @param @param value
	 * @param @param timeOut 数据失效时间，单位毫秒
	 * @return void 返回类型
	 *
	 */
	public static void put(Object key, Object value, long timeOut) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
		LocalItem item = new LocalItem(timeOut, System.currentTimeMillis(),
				value);
		cache.put(key, item);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Object key) {
		LocalItem item = cache.get(key);
		if (item == null) {
			return null;
		}
		return (T) item.getValue();

	}

	public static void remove(Object key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}

	private static void executeTaskProcess() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					if (!cache.isEmpty()) {
						for (Entry<Object, LocalItem> entry : cache.entrySet()) {
							LocalItem item = entry.getValue();
							if (item != null) {
								long currentTime = System.currentTimeMillis();
								// 移除过期时间
								if (item.getCacheTime() > 0
										&& currentTime - item.getCreateTime() > item
												.getCacheTime()) {
									System.out
											.println("LocalCache executeTaskProcess remove key:"
													+ entry.getKey());
									cache.remove(entry.getKey());
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, 1000 * 60);
	}
}

class LocalItem {
	// 缓存时间：单位毫秒
	private long cacheTime;
	// 创建时间：单位毫秒
	private long createTime;
	// 缓存值
	private Object value;

	public LocalItem(long cacheTime, long createTime, Object value) {
		this.cacheTime = cacheTime;
		this.createTime = createTime;
		this.value = value;
	}

	public long getCacheTime() {
		return cacheTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public Object getValue() {
		return value;
	}

}
