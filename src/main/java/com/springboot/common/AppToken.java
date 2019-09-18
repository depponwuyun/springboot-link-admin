package com.springboot.common;

import com.link.tool.utils.MD5Utils;
import com.link.tool.utils.UUIDUtils;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.IPUtils;

/**
 * global only identifier
 * 
 * @author Administrator
 *
 */
public class AppToken {

	/**
	 * thread security of generate token
	 * 
	 * @param request
	 * @return
	 */
	public static synchronized String generateToken() {

		StringBuilder token = new StringBuilder();
		token.append(HttpUtils.getSession().getId());
		token.append("_");
		token.append(UUIDUtils.generateUUID());
		token.append("_");
		token.append(IPUtils.getIpAddr(HttpUtils.getRequest()));
		return MD5Utils.getMD5AndBase64(token.toString());

	}

}
