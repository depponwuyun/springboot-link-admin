package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.bcode.service.IDataScopeService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;


@Controller
@RequestMapping(value = "/dataScope")
public class DataScopeRest extends BaseRest {

	@Autowired
	private IDataScopeService dataScopeService;

	@RequestMapping(value = "queryAllCheckByRole", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult queryAllCheckByRole(@RequestBody DataScope ds) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(dataScopeService.queryAllCheckByRole(ds));
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
