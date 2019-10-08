package com.springboot.common;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.redis.RedisUtils;
import com.springboot.core.web.session.CookieContext;

public class GlobalUser {

	public final static Integer user_enable = 1;// 正常
	public final static Integer user_unable = 2;// 禁用

	private static final String USER_INFO = "user_info_";

	/**
	 * set user informateion to redis
	 * 
	 * @param userInfoContext
	 */
	public static void setUserInfo(UserInfo userInfo) {
		if (userInfo == null) {
			return;
		}
		RedisUtils.getRedis().set(USER_INFO + "" + userInfo.getToken(),
				JSONObject.toJSONString(userInfo), 90, TimeUnit.DAYS);
	}

	/**
	 * get user information to redis
	 * 
	 * @param token
	 * @return
	 */
	public static UserInfo getUserInfo() {
		String token = getToken();
		if (StringUtils.isBlank(token)) {
			return null;
		}
		UserInfo userInfo = null;
		String str = RedisUtils.getRedis().get(USER_INFO + "" + token);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		userInfo = JSONObject.parseObject(str, UserInfo.class);

		return userInfo;
	}

	public static void destroyUser() {
		RedisUtils.getRedis().delete(USER_INFO + "" + getToken());
	}

	public static String getToken() {
		String token = HttpUtils.getRequest().getHeader(AppContext.TOKEN);
		if (StringUtils.isBlank(token)) {
			token = CookieContext.get(AppContext.TOKEN);
		}
		return token;
	}

}
