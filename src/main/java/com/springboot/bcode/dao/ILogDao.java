package com.springboot.bcode.dao;

import com.springboot.bcode.domain.logs.BLog;
import com.springboot.bcode.domain.logs.BLogVO;
import com.springboot.core.web.mvc.JqGridPage;

public interface ILogDao {

	JqGridPage<BLog> selectPage(BLogVO  log);

	int insert(BLog log);

}
