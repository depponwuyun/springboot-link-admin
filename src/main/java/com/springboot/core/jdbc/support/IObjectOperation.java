package com.springboot.core.jdbc.support;

import java.util.Collection;
import java.util.List;

public interface IObjectOperation {
	/**
	 * 往@Tables表中插入数据 <br/>
	 * 使用说明 ：将传入的entity对象中用@Columns标注的字段值插入到数据库中@Tables表中
	 * 
	 * @param entity
	 *            要插入数据对象
	 * @return int 返回类型 返回值为0表示执行成功，-1表示执行失败
	 *
	 */
	<T> int insert(T entity);

	/**
	 * insertReturnAutoIncrement 插入数据 并返回对应的主键自增<br/>
	 * 使用说明 ：将传入的entity对象中用@Columns标注的字段值插入到数据库中@Tables表中
	 * 
	 * @param entity
	 *            插入数据对象
	 * @return Integer 返回类型 返回值为主键自增
	 *
	 */
	<T> Integer insertReturnAutoIncrement(T entity);

	/**
	 * 更新@Tables表中数据<br/>
	 * 
	 * 使用说明
	 * ：将传入的entity对象中用@Columns标注的字段，数据更新到数据库中@Tables表中，更新条件@Columns中primaryKey =
	 * true
	 * 
	 * @param entity
	 *            要更新数据对象
	 * 
	 * @return int 返回类型 返回值为0表示执行成功，-1表示执行失败
	 *
	 */
	<T> int update(T entity);

	/**
	 * 删除 @Tables表中数据<br/>
	 * 
	 * 使用说明 ：根据传入的entity对象中用@Columns标注的字段，且字段值不为空 作为删除条件
	 * 
	 * 
	 * @param entity
	 *            删除数据对象条件
	 * 
	 * @return int 返回类型 返回值为0表示执行成功，-1表示执行失败
	 *
	 */
	<T> int delete(T entity);

	<T> int[] batchUpdate(Collection<T> collection);

	<T> int[] batchInsert(Collection<T> collection);

	<T> int[] batchDelete(Collection<T> collection);

	/**
	 * 
	 * 查询@Tables中某条数据 <br/>
	 * 使用说明：根据传入主键id 查询 @Tables表中数据<br/>
	 *
	 * @param @param id 主键id
	 * @param @param tClass 返回目标类类型
	 * @return T 返回单个对象
	 *
	 */
	<T> T findById(Object id, Class<T> tClass);

	/**
	 * 
	 * 查询@Tables中数据列表 <br/>
	 * 使用说明：根据传入entity中用@Columns标注的字段，且字段值不为空作为查询条件
	 *
	 * @param entity
	 *            查询条件
	 * @return List<T> 返回结果集
	 *
	 */
	<T> List<T> find(T entity);

	/**
	 * 
	 * 查询@Tables中某条数据 <br/>
	 * 
	 * 使用说明：根据传入entity中用@Columns标注的字段，且字段值不为空作为查询条件
	 * 
	 * @param @param entity
	 * @param @return 设定文件
	 * @return T 返回单个对象
	 *
	 */
	<T> T findOne(T entity);

}
