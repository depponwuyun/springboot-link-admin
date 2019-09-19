package com.springboot.common.utils;

import java.util.UUID;

/**
 * generate only 32char
 * 
 * @author Administrator
 *
 */
public class UUIDUtils {

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString(); // 获取UUID并转化为String对象
		uuid = uuid.replace("-", "");
		return uuid;
	}

}
