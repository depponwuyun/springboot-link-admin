package com.springboot.bcode.dao;

import com.springboot.bcode.domain.auth.BLog;
import com.springboot.core.web.mvc.JqGridPage;

public interface ILogDao {

	JqGridPage<BLog> selectPage(BLog log);

	int insert(BLog log);

	public int update(BLog b);
}
