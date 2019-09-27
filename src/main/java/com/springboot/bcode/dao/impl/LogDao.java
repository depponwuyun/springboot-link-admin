package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.logs.BLog;
import com.springboot.bcode.domain.logs.BLogVO;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.JqGridPage;

@Repository
public class LogDao extends BaseDaoImpl implements ILogDao {

	@Override
	public JqGridPage<BLog> selectPage(BLogVO log) {

		List<BLog> list = super.select(
				getSqlPageHandle().handlerPagingSQL(logPageSql(log, 0),
						log.getPage(), log.getLimit()), null, BLog.class);
		int count = super.jdbcTemplate.queryForObject(logPageSql(log, 1), null,
				Integer.class);
		JqGridPage<BLog> page = new JqGridPage<BLog>(list, count,
				log.getLimit(), log.getPage());
		return page;
	}

	private String logPageSql(BLogVO log, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append("select  * from t_web_logs");
		} else {
			sql.append("select count(*) from t_web_logs ");
		}
		sql.append(" where 1=1");

		sql.append(" and cratetime >='" + log.getStarttime() + "'");

		sql.append(" and cratetime <='" + log.getEndtime() + "'");

		if (StringUtils.isNotBlank(log.getLoginuser())) {
			sql.append(" and loginuser like '%").append(
					log.getLoginuser().trim() + "%' ");
		}
		if (type == 0) {
			sql.append(" order by cratetime desc");
		}
		return sql.toString();
	}

	@Override
	public int insert(BLog log) {
		return super.insert(log);
	}

}
