package com.springboot.bcode.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserRoleVO;
import com.springboot.bcode.service.ILogService;
import com.springboot.bcode.service.IUserService;
import com.springboot.common.GlobalUser;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.JqGridPage;
import com.springboot.core.web.mvc.ResponseResult;

@RestController
@RequestMapping(value = "/rest/user")
public class UserRest extends BaseRest {

	@Autowired
	private IUserService userService;

	@Autowired
	private ILogService logService;

	@RequestMapping(value = "gets")
	public ResponseResult gets() {
		ResponseResult rep = new ResponseResult();
		rep.setResult("hello");
		return rep;
	}

	@OpertionBLog(title = "登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult login(@RequestBody LoginVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.login(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("登陆异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 当前在线用户的信息包括角色权限菜单等
	 *
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "info")
	public ResponseResult info() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.info());
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "退出")
	@RequestMapping("logout")
	public ResponseResult logout() {
		ResponseResult rep = new ResponseResult();
		try {
			GlobalUser.destroyUser();
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public JqGridPage<UserInfo> list(UserInfo user) {
		JqGridPage<UserInfo> page = null;
		try {
			page = userService.queryPage(user);
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return page;

	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@OpertionBLog(title = "添加用户")
	@Requestauthorize
	public ResponseResult add(@RequestBody UserInfo user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.add(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "queryById", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult queryById(String uid) {
		ResponseResult rep = new ResponseResult();
		try {
			UserInfo user = userService.find(uid);
			rep.setResult(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("查询异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	@OpertionBLog(title = "修改用户")
	@Requestauthorize
	public ResponseResult modify(@RequestBody UserInfo user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modify(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	@OpertionBLog(title = "删除用户")
	@Requestauthorize
	public ResponseResult remove(String uid) {
		ResponseResult rep = new ResponseResult();
		try {
			// userService.remove(uid);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("删除异常.请稍后再试");
		}
		return rep;
	}

	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	@OpertionBLog(title = "修改密码")
	@ResponseBody
	public ResponseResult modifyPwd(HttpSession session,
			@RequestParam(value = "newPassword") String newPassword,
			@RequestParam(value = "enNewpassword") String enNewpassword,
			@RequestParam(value = "oldPassword") String oldPassword) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modifyPwd(session, newPassword, enNewpassword,
					oldPassword);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}

	@OpertionBLog(title = "用户分配角色")
	@RequestMapping(value = "saveRelationRole", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult saveRelationRole(@RequestBody UserRoleVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.saveRelationRole(vo);
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
