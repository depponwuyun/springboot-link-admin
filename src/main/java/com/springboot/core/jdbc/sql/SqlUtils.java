package com.springboot.core.jdbc.sql;

import java.lang.reflect.Field;

import com.link.tool.lang.StringUtils;
import com.springboot.core.jdbc.JdbcException;
import com.springboot.core.jdbc.annotation.Tables;

public class SqlUtils {

	public static <T> void validateTargetClass(T entity) {
		if (entity == null) {
			throw new JdbcException("sql@Tables对象不能为空");
		}
		Class<?> classz = entity.getClass();
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		if (linkTable == null) {
			throw new JdbcException(classz + "类中没有注解类@Tables");
		}
		String tableName = linkTable.table();
		if (StringUtils.isBlank(tableName)) {
			throw new JdbcException(classz + "类中@Tables的table值为空");
		}
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		if (fields == null || fields.length < 0) {
			throw new JdbcException(classz + "类中没有定义属性");
		}
	}

	public static boolean typeIsNotNullValue(Class<?> type, Object filedValue) {

		// String
		if (type == java.lang.String.class) {
			if (filedValue == null || filedValue == "") {
				return false;
			}
			// Number
		} else if (type == java.lang.Integer.class
				|| type == java.lang.Double.class
				|| type == java.lang.Float.class
				|| type == java.lang.Long.class
				|| type == java.lang.Short.class
				|| type == java.math.BigDecimal.class) {
			if (null == filedValue) {
				return false;
			}
			// Date
		} else if (type == java.util.Date.class
				|| type == java.sql.Timestamp.class
				|| type == java.sql.Date.class) {
			if (null == filedValue) {
				return false;
			}
			// Boolean
		} else if (type == java.lang.Boolean.class) {
			if (null == filedValue) {
				return false;
			}
		} else if (type == java.lang.Byte.class) {
			if (null == filedValue) {
				return false;
			}
		}
		return true;
	}

}
