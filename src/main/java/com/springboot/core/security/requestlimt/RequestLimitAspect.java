package com.springboot.core.security.requestlimt;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.link.tool.http.IPUtils;
import com.springboot.core.redis.IRedis;
import com.springboot.core.redis.RedisUtils;

/**
 * 请求频率限制
 * 
 * @ClassName: RequestLimitAspect
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2019年9月11日 上午8:47:52
 *
 */
@Aspect
@Component
@Order(1)
public class RequestLimitAspect {

	private static final String REQ_LIMIT = "req_limit_";

	/**
	 * 定义拦截规则：拦截com.link.security.springboot.controller包下面的所有类中，有@RequestLimitAnnotation注解的方法
	 * 。
	 */
	@Around("execution(* com.springboot.bcode.api..*(..)) "
			+ "and @annotation(com.springboot.core.security.requestlimt.RequestLimit)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		RequestLimit limt = method.getAnnotation(RequestLimit.class);
		// No request for limt,continue processing request
		if (limt == null) {
			return pjp.proceed();
		}

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		int time = limt.time();
		int count = limt.count();
		int waits = limt.waits();

		String ip = IPUtils.getIpAddr(request);
		String url = request.getRequestURI();

		// judge codition
		String key = requestLimitKey(url, ip);
		IRedis redis = RedisUtils.getRedis();
		int nowCount = redis.get(key) == null ? 0 : Integer.valueOf(redis
				.get(key));

		if (nowCount == 0) {
			nowCount++;
			redis.set(key, String.valueOf(nowCount), time);
			return pjp.proceed();
		} else {
			nowCount++;
			redis.set(key, String.valueOf(nowCount));
			if (nowCount >= count) {
				System.out.println("用户IP[" + ip + "]访问地址[" + url + "]访问次数["
						+ nowCount + "]超过了限定的次数[" + count + "]限定时间[" + waits
						+ "秒]");
				redis.expire(key, waits, TimeUnit.SECONDS);
				return returnLimit(request);
			}
		}

		long start = System.currentTimeMillis();
		System.out.println("AppRequestLimtAspect Aspect 耗时:"
				+ (System.currentTimeMillis() - start));
		return pjp.proceed();
	}

	/**
	 * requestLimitKey: url_ip
	 * 
	 * @param url
	 * @param ip
	 * @return
	 */
	private static String requestLimitKey(String url, String ip) {

		StringBuilder sb = new StringBuilder();
		sb.append(REQ_LIMIT);
		sb.append(url);
		sb.append("_");
		sb.append(ip);
		return sb.toString();
	}

	/**
	 * 返回拒绝信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String returnLimit(HttpServletRequest request) throws IOException {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		out.println("{\"code\":5003, \"msg\":\"service reject request!\"}");
		out.flush();
		out.close();
		return null;

	}

}
