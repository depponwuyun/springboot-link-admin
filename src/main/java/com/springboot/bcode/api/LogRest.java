package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.logs.BLogVO;
import com.springboot.bcode.service.ILogService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@RestController
@RequestMapping(value = "/rest/logs")
public class LogRest extends BaseRest {
	@Autowired
	private ILogService logService;

	@Requestauthorize
	@RequestMapping(value = "/blog/list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody BLogVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(logService.queryPage(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}
}
