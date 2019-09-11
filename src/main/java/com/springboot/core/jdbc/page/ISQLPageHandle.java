package com.springboot.core.jdbc.page;

public interface ISQLPageHandle {
	/**
	 * 将传入的SQL做分页处理
	 * 
	 * @param String
	 *            oldSql 原SQL
	 * @param int pageNo 第几页，用来计算first 这个值由（pageNo-1）*pageSize
	 * @param int pageSize 每页数量
	 * */
	public String handlerPagingSQL(String sql, int pageNo, int pageSize);
}
