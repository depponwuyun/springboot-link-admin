package com.springboot.core.logger;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.auth.BLog;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.common.GlobalUser;

/**
 * 日志记录aop,目前已经优化为线程池加异步写入业务日志
 * 
 * @author Administrator
 *
 */
/*@Order(7)
@Aspect
@Component
public class OpertionBLogAspect {
	private static ExecutorService pool = Executors.newFixedThreadPool(5);

	@Autowired
	private ILogDao logDao;

	*//**
	 * 定义拦截规则：拦截com.springboot.bcode.controller包下面的所有类中，有@OpertionBLog注解的方法 。 +
	 * "and @annotation(com.springboot.core.logger.OpertionBLog)"
	 *//*
	@Around("execution(* com.springboot.bcode.controller..*(..))")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		UserInfo app = GlobalUser.getUserInfo();
		BLog log = new BLog();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		try {
			// 先通过注解判断
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Method method = signature.getMethod(); // 获取被拦截的方法
			OpertionBLog opertionBLog = method
					.getAnnotation(OpertionBLog.class);
			if (opertionBLog == null) {
	
			} else {
				log.setTitle(opertionBLog.title());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.setTimestamp(System.currentTimeMillis());
		log.setCratetime(new java.sql.Timestamp(new java.util.Date().getTime()));

		// 方法执行后记录日志
		Object result = pjp.proceed();
		try {
			crateBLog(log, pjp, request);
			log.setDuration(System.currentTimeMillis() - log.getTimestamp());
			log.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将有OpertionBLog标记的日志记录到数据库
		//pool.execute(new WBLog(log));

		return result;
	}

	



	private void crateBLog(BLog log, ProceedingJoinPoint pjp,
			HttpServletRequest request) {
		log.setId(UUIDUtils.generateUUID());

		log.setUrl(request.getRequestURL().toString());
		log.setClientIp(IPUtils.getIpAddr(request));
		log.setServerIp(ServerUtils.getHostAddress());
		log.setIp(IPUtils.getIpAddr(request));

		String requestmethod = request.getMethod();
		String contentType = request.getContentType();
		Object[] args = pjp.getArgs();

		log.setRequestmethod(requestmethod);
		log.setContentType(contentType);
		log.setRequestparams(getRequestParams(request, args));

	}

	private Object getRequestParams(HttpServletRequest request, Object[] args) {
		StringBuilder sb = new StringBuilder();
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			if (request.getContentType() != null) {
				if (request.getContentType().indexOf("json") > -1) {
					for (Object object : args) {
						sb.append(JSONObject.toJSONString(object));
					}
				} else {
					for (Object object : args) {
						sb.append(object);
					}
				}
			}
		} else {
			sb.append(request.getQueryString());
		}
		return sb.toString();
	}

	class WBLog implements Runnable {
		private BLog log;

		public WBLog(BLog log) {
			this.log = log;
		}

		@Override
		public void run() {
			if (log != null) {
				try {
					logDao.insert(log);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}*/
