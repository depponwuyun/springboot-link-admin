package com.springboot.core.jdbc.page;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SQLPageHandleConfig {
	@Bean(name = "mysqlSQLPageHandle")
	@Qualifier("mysqlSQLPageHandle")
	@Primary
	public ISQLPageHandle mysqlDataSource() {
		return new MysqlSQLPageHandleImpl();
	}

	@Bean(name = "oracleSQLPageHandle")
	@Qualifier("oraclePageHandle")
	public ISQLPageHandle oracleDataSource() {
		return new MysqlSQLPageHandleImpl();
	}
}
