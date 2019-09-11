package com.springboot.bcode.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.service.IRightService;
import com.springboot.common.GlobalUser;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@Controller
@RequestMapping(value = "/right")
public class RightRest extends BaseRest {

	@Autowired
	private IRightService rightService;

	@RequestMapping("manager")
	public String toUserManage() {
		return "auth/right_manager";
	}

	/**
	 * 获取用户拥有的功能权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ownedFunctionRight")
	@ResponseBody
	public ResponseResult ownedFunctionRight(HttpServletRequest request) {
		ResponseResult rep = new ResponseResult();
		try {
			//UserInfo user = GlobalUser.getCurrentUser();
			//rep.setResult(user.getOwnedFunctionList());
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	@ResponseBody
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			Permission right = new Permission();
			List<Permission> list = rightService.queryAll(right);
			rep.setResult(list);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 根据父类id查询直接下级（只包含一级）；
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "queryChild")
	@ResponseBody
	public ResponseResult queryChild(
			@RequestParam(value = "parentId", required = false) Integer parentId) {
		ResponseResult rep = new ResponseResult();
		try {
			List<Permission> list = rightService.queryChild(parentId);
			rep.setResult(list);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/*
	 * @RequestMapping(value = "queryAllASRecursion")
	 * 
	 * @ResponseBody public ResponseResult queryAllASRecursion() {
	 * ResponseResult rep = new ResponseResult(); try { Right right = new
	 * Right(); List<Right> list = rightService.queryAll(right);
	 * rep.setResult(list); } catch (AuthException e) { rep.setCode(CODE_500);
	 * rep.setMsg(e.getMessage()); } catch (Exception e) {
	 * rep.setCode(CODE_500); rep.setMsg("系统异常.请稍后再试"); } return rep; }
	 */

	@RequestMapping(value = "queryAllCheckByRole/{code}")
	@ResponseBody
	public ResponseResult queryAllCheckByRole(@PathVariable("code") Integer code) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(rightService.queryAllCheckByRole(code));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}
	@OpertionBLog(title = "新增权限")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult save(@RequestBody Permission right) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.save(right);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}

		return rep;
	}

	@RequestMapping(value = "toModify/{code}")
	@ResponseBody
	public ResponseResult toModify(@PathVariable("code") Integer code) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(rightService.query(code));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}
	@OpertionBLog(title = "修改权限")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult modify(@RequestBody Permission right) {
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
	@OpertionBLog(title = "删除权限")
	@RequestMapping(value = "remove/{code}")
	@ResponseBody
	public ResponseResult remove(@PathVariable("code") Integer code) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.delete(code);
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
