package com.springboot.bcode.service;

import com.springboot.bcode.domain.auth.BLog;
import com.springboot.core.web.mvc.JqGridPage;



public interface ILogService {

	JqGridPage<BLog> queryPage(BLog log);
	 int update (BLog b) ;
}
