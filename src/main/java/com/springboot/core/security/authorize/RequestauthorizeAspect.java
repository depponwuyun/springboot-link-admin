package com.springboot.core.security.authorize;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

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
import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.common.GlobalUser;
import com.springboot.core.logger.LoggerUtil;

/**
 * 权限验证aop
 * 
 * @author Administrator
 *
 */
@Order(2)
@Aspect
@Component
public class RequestauthorizeAspect {

	/**
	 * 定义拦截规则：拦截com.springboot.bcode.api包下面的所有类中，有@Requestauthorize注解的方法 。
	 */
	@Around("execution(* com.springboot.bcode.api..*(..)) "
			+ "and @annotation(com.springboot.core.security.authorize.Requestauthorize)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		Requestauthorize limt = method.getAnnotation(Requestauthorize.class);
		if (limt == null) {
			return pjp.proceed();
		}

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpServletResponse response = requestAttributes.getResponse();

		UserInfo userInfo = GlobalUser.getUserInfo();
		if (userInfo == null) {
			try {
				return returnAuthorizeRequests(request, response);
			} catch (IOException e) {
			}
		}

		String ip = IPUtils.getIpAddr(request);
		String url = request.getRequestURI();

		List<Permission> permissions = userInfo.getPermissions();
		if (permissions == null || permissions.isEmpty()) {
			try {
				LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂无权限");
				return returnAuthorizeRequests(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Permission currentRight = null;
			for (Permission right : permissions) {
				if (url.equals(right.getUrl())) {
					currentRight = right;
					break;
				}
			}
			if (currentRight == null) {
				try {
					LoggerUtil.warn("用户IP[" + ip + "]访问地址[" + url + "]暂无权限");
					return returnAuthorizeRequests(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return pjp.proceed();
	}

	private String returnAuthorizeRequests(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		out.println("{\"code\":5002, \"msg\":\"no permission, please contact the administrator!\"}");
		out.flush();
		out.close();
		return null;
	}

}
