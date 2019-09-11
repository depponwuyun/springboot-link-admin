package com.springboot.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * - TODO(描述类的职责) 自定义日志类 ;后续需要扩展自己添加
 * 
 * @version V1.0
 *          <p style="display:none">
 *          modifyRecord
 *          </p>
 *          <p style="display:none">
 *          version:V1.0,author:252956,date:2017-2-8上午10:22:02,content:TODO
 *          </p>
 * @author 252956
 * @date 2017-2-8上午10:22:02
 * @since
 * 
 */
public class LoggerUtil {

	/**
	 * fqcn
	 */
	private static final String fqcn = LoggerUtil.class.getName();
	/**
	 * NOT_AVAIL
	 */
	private static final String NOT_AVAIL = "?";

	/**
	 * 获取最原始被调用的堆栈信息
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017-2-8上午10:33:00
	 * @param stackTrace
	 * @return
	 * 
	 */
	private static StackTraceElement getStackTraceElement(
			final StackTraceElement[] stackTrace) {
		boolean next = false;
		for (final StackTraceElement element : stackTrace) {
			final String className = element.getClassName();
			if (next && !fqcn.equals(className)) {
				return element;
			}
			if (fqcn.equals(className)) {
				next = true;
			} else if (NOT_AVAIL.equals(className)) {
				break;
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)info
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:08
	 * @param message
	 *
	 */
	public static void info(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.info(message);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)info
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:15
	 * @param message
	 * @param t
	 *
	 */
	public static void info(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.info(message, t);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)debug
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:21
	 * @param message
	 *
	 */
	public static void debug(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.debug(message);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)debug
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:27
	 * @param message
	 * @param t
	 *
	 */
	public static void debug(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.debug(message, t);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)error
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:32
	 * @param message
	 *
	 */
	public static void error(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.error(message);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)error
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:37
	 * @param message
	 * @param t
	 *
	 */
	public static void error(final String message, Throwable t) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.error(message, t);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)warn
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:16:42
	 * @param message
	 *
	 */
	public static void warn(final String message) {
		StackTraceElement caller = getStackTraceElement(Thread.currentThread()
				.getStackTrace());
		if (null == caller)
			return;
		Logger log = LoggerFactory
				.getLogger(caller.getClassName() + "." + caller.getMethodName()
						+ "() Line: " + caller.getLineNumber());
		log.warn(message);
	}
}
