package com.springboot.core.jdbc.support;

import java.util.List;

public interface ISqlOperation {

	/**
	 * 执行增删改sql
	 *
	 * @param @param sql
	 * @param @param params
	 * @param @return 设定文件
	 * @return int 返回类型
	 *
	 */
	int addOrUpdateOrDelete(String sql, Object[] params);

	/**
	 * 根据sql查询
	 *
	 * @param @param sql
	 * @param @param tClass
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 *
	 */

	<T> List<T> select(String sql, Class<T> tClass);

	/**
	 * 根据sql查询
	 *
	 * @param @param sql sql语句
	 * @param @param params 查询条件
	 * @param @param tClass 转换目标类
	 * @return List<T> 返回查询结果集
	 *
	 */
	<T> List<T> select(String sql, Object[] params, Class<T> tClass);

	/**
	 * 
	 * 根据sql查询
	 * 
	 * @param @param sql
	 * @param @param tClass
	 * @param @return 设定文件
	 * @return T 返回类型
	 *
	 */
	<T> T selectOne(String sql, Class<T> tClass);
}
