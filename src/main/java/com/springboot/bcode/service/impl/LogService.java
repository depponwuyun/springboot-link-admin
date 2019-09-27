package com.springboot.bcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bcode.dao.ILogDao;
import com.springboot.bcode.domain.logs.BLog;
import com.springboot.bcode.domain.logs.BLogVO;
import com.springboot.bcode.service.ILogService;
import com.springboot.common.exception.AuthException;
import com.springboot.common.utils.DateUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class LogService implements ILogService {
	@Autowired
	private ILogDao logDao;

	@Override
	public JqGridPage<BLog> queryPage(BLogVO vo) {
		if (vo == null) {
			throw new AuthException("参数不能为空");
		}
		if (StringUtils.isBlank(vo.getStarttime())
				|| StringUtils.isBlank(vo.getEndtime())) {
			throw new AuthException("请选择时间范围");
		}
		if (DateUtils.differentDays(
				DateUtils.formatDateTime(vo.getStarttime()),
				DateUtils.formatDateTime(vo.getEndtime())) > 1
				|| DateUtils.differentDays(
						DateUtils.formatDateTime(vo.getStarttime()),
						DateUtils.formatDateTime(vo.getEndtime())) < 0) {
			throw new AuthException("时间范围不能大于一天");
		}
		return logDao.selectPage(vo);
	}

}
