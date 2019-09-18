package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.ModifyPwdVO;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserInfoVO;
import com.springboot.bcode.service.IUserService;
import com.springboot.common.GlobalUser;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@RestController
@RequestMapping(value = "/rest/user")
public class UserRest extends BaseRest {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "gets")
	public ResponseResult gets() {
		ResponseResult rep = new ResponseResult();
		rep.setResult("hello");
		return rep;
	}

	@OpertionBLog(title = "登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
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

	@Requestauthorize
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody UserInfo user) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.queryPage(user));
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	@Requestauthorize
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OpertionBLog(title = "添加用户")
	public ResponseResult add(@RequestBody UserInfoVO user) {
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

	@Requestauthorize
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OpertionBLog(title = "修改用户")
	public ResponseResult update(@RequestBody UserInfoVO user) {
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

	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	@OpertionBLog(title = "修改密码")
	public ResponseResult modifyPwd(@RequestBody ModifyPwdVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modifyPwd(vo);
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
