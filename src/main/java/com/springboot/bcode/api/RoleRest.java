package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RoleDataScopeVO;
import com.springboot.bcode.service.IRoleService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@RestController
@RequestMapping(value = "/rest/role")
public class RoleRest extends BaseRest {

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Role role) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(roleService.queryPage(role));
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	@RequestMapping(value = "all")
	public ResponseResult all() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(roleService.queryAll());
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "新增角色")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Role role) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.add(role);
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
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "修改角色")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Role role) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.update(role);
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
	 * 移除角色
	 * 
	 * @param id
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "删除角色")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.delete(id);
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
	 * 分配角色对应的数据权限
	 * 
	 * @param roleRelationRightVO
	 * @return
	 */
	@OpertionBLog(title = "角色分配数据权限")
	@RequestMapping(value = "saveRelationDataScope", method = RequestMethod.POST)
	@Requestauthorize
	public ResponseResult saveRelationDataScope(@RequestBody RoleDataScopeVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.saveRelationDataScope(vo);
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
