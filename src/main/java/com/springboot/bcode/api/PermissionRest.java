package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.service.IPermissionService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.algorithm.PermissionRecursion;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@RestController
@RequestMapping(value = "rest/permission")
public class PermissionRest extends BaseRest {

	@Autowired
	private IPermissionService rightService;

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	@Requestauthorize
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(PermissionRecursion.recursion(rightService.queryAll()));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "allByRole/{roleId}")
	public ResponseResult queryAllCheckByRole(
			@PathVariable("roleId") String roleId) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(PermissionRecursion.recursion(rightService
					.queryAllByRole(roleId)));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "新增权限")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Permission permission) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.save(permission);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}

		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "修改权限")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Permission right) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.modify(right);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "删除权限")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.delete(id);
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
