package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bcode.domain.auth.BLog;
import com.springboot.bcode.service.ILogService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.web.mvc.JqGridPage;

@Controller
@RequestMapping(value = "/log")
public class LogRest {
	@Autowired
	private ILogService logService;

	@RequestMapping(value = "manager")
	public String getLoglist() {
		return "auth/blog";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public JqGridPage<BLog> list(BLog log) {
		JqGridPage<BLog> page = null;
		try {
			page = logService.queryPage(log);
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return page;

	}

}
