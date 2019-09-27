package com.springboot.bcode.service;

import com.springboot.bcode.domain.logs.BLog;
import com.springboot.bcode.domain.logs.BLogVO;
import com.springboot.core.web.mvc.JqGridPage;



public interface ILogService {

	JqGridPage<BLog> queryPage(BLogVO vo);
}
