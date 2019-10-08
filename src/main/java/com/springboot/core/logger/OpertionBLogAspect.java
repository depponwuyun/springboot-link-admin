package com.springboot.core.logger;

import java.lang.reflect.Method;
import java.util.Date;
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

import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.logs.BLog;
import com.springboot.common.GlobalUser;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.IPUtils;

/**
 * 日志记录aop,目前已经优化为线程池加异步写入业务日志
 * 
 * @author Administrator
 *
 */
@Order(7)
@Aspect
@Component
public class OpertionBLogAspect {
	private static ExecutorService pool = Executors.newFixedThreadPool(5);

	@Autowired
	private ILogDao logDao;

	@Around("execution(* com.springboot.bcode.api..*(..)) "
			+ "and @annotation(com.springboot.core.logger.OpertionBLog)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		BLog log = new BLog();
		log.setTimestamp(System.currentTimeMillis());
		log.setCratetime(new Date());

		// 先通过注解判断
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		OpertionBLog opertionBLog = method.getAnnotation(OpertionBLog.class);
		// 方法执行后记录日志
		Object result = pjp.proceed();

		if (opertionBLog == null) {
			return result;
		}
		try {
			crateBLog(log, pjp);
			log.setTitle(opertionBLog.title());
			log.setDuration(System.currentTimeMillis() - log.getTimestamp());
			log.setResult(result);
			// 将有OpertionBLog标记的日志记录到数据库
			pool.execute(new WBLog(log));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private void crateBLog(BLog log, ProceedingJoinPoint pjp) {
		HttpServletRequest request = HttpUtils.getRequest();
		log.setUrl(request.getRequestURL().toString());
		log.setIp(IPUtils.getIpAddr(request));

		//String requestmethod = request.getMethod();
		//String contentType = request.getContentType();
		//log.setRequestmethod(requestmethod);
		//log.setContentType(contentType);

		UserInfo user = GlobalUser.getUserInfo();
		if (user != null) {
			log.setLoginuser(user.getName());
			log.setVsername(user.getVserName());
		}

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

}
