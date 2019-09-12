package com.springboot.bcode.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.link.tool.lang.StringUtils;
import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.auth.BLog;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.JqGridPage;

@Repository
public class LogDao extends BaseDaoImpl implements ILogDao {

	@Override
	public JqGridPage<BLog> selectPage(BLog log) {
		int startResult = (log.getPage() - 1) * log.getLimit()+ 1;;
		int endResule = (log.getPage() * log.getLimit());

		StringBuilder sql = new StringBuilder();
		StringBuilder sqlOrderBy = new StringBuilder();
		sql.append("select * from t_web_logs");
		sql.append("  where 1=1");
		if (log.getStarttime() != null && !"".equals(log.getStarttime())) {
			sql.append(" and cratetime >='").append(
					log.getStarttime() + " 00:00:00' ");
		}
		if (log.getEndtime() != null && !"".equals(log.getEndtime())) {
			sql.append(" and cratetime <='").append(
					log.getEndtime() + " 23:59:59' ");
		}
		if (log.getLoginuser() != null && !"".equals(log.getLoginuser())) {
			sql.append(" and loginuser like '%").append(
					log.getLoginuser().trim() + "%' ");
		}
		if (StringUtils.isNotBlank(log.getSidx())) {
			if ((log.getSord().trim().equalsIgnoreCase("asc"))) {
				sqlOrderBy.append(" order by " + log.getSidx().split(" ")[0]
						+ " asc");
			} else {
				sqlOrderBy.append(" order by " + log.getSidx().split(" ")[0]
						+ " desc");
			}
		} else {
			sqlOrderBy.append(" order by cratetime  desc");
		}
		StringBuffer newsql = new StringBuffer();
		newsql.append("select * from(select row_number() over (" + sqlOrderBy
				+ ") rownumber,* from( ");
		newsql.append(sql);
		newsql.append(")a )b where rownumber between " + startResult + " and "
				+ endResule);

		List<BLog> list = super.find(newsql.toString(), null, BLog.class);
		List<BLog> count = super.find(sql.toString(), null, BLog.class);
		JqGridPage<BLog> page = new JqGridPage<BLog>(list, count.isEmpty() ? 0
				: count.size(), log.getLimit(), log.getPage());
		return page;
	}

	@Override
	public int insert(BLog log) {
		return super.insert(log);
	}

	public int update(BLog b) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(id) as id from t_web_logs ");
		sql.append(" where 1=1 ");
		if (b.getIp() != null && !"".equals(b.getIp())) {
			sql.append(" and ip = '").append(b.getIp()).append("'");
		}
		if (b.getLoginuser() != null && !"".equals(b.getLoginuser())) {
			sql.append(" and loginuser = '").append(b.getLoginuser())
					.append("'");
		}
		List<BLog> list = super.find(sql.toString(), null, BLog.class);
		if (list.size() > 0 && list != null) {
			String obj = list.get(0).getId();
			BLog b1 = super.findById(Integer.parseInt(obj), BLog.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			b1.setTcsj(sdf.format(new Date()));
			return super.update(b1);
		}
		return 0;
	}

}
