package com.springboot.core.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springboot.core.jdbc.page.ISQLPageHandle;
import com.springboot.core.jdbc.support.AbstractJdbcSupport;

public class BaseDaoImpl extends AbstractJdbcSupport {
	@Autowired
	@Qualifier("baseJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	/**
	 * 分页处理
	 */
	@Autowired
	@Qualifier("mysqlSQLPageHandle")
	protected ISQLPageHandle mysqlSQLPageHandle;

	@Override
	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	protected ISQLPageHandle getSqlPageHandle() {
		return mysqlSQLPageHandle;
	}

}
