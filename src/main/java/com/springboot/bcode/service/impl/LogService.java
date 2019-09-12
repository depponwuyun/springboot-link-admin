package com.springboot.bcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.auth.BLog;
import com.springboot.bcode.service.ILogService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class LogService implements ILogService {
	@Autowired
	private ILogDao logDao;

	@Override
	public JqGridPage<BLog> queryPage(BLog log) {
		if (log == null)
			throw new AuthException("参数不能为空");
		return logDao.selectPage(log);
	}
	
	public int update (BLog b) {
		return logDao.update( b);
	}
}
