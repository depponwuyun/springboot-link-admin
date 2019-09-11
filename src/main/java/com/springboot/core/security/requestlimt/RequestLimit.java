package com.springboot.core.security.requestlimt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * one minutes request frequency is Fifty times, exceeding the wait five minutes
 * @author Administrator
 *
 */
public @interface RequestLimit {

	int time() default 60;

	int count() default 50;

	int waits() default 300;

}
