package com.springboot.bcode.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RoleDataScopeVO;
import com.springboot.bcode.domain.auth.RolePermissionVO;
import com.springboot.bcode.service.IRoleService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.LoggerUtil;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.security.authorize.Requestauthorize;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.JqGridPage;
import com.springboot.core.web.mvc.ResponseResult;

@Controller
@RequestMapping(value = "/role")
public class RoleRest extends BaseRest {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("manager")
	public String manager() {
		return "auth/role_manager";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public JqGridPage<Role> list(Role role) {
		JqGridPage<Role> page = null;
		try {
			page = roleService.queryPage(role);
		} catch (AuthException e) {
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
		return page;

	}

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	@ResponseBody
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			Role role = new Role();
			List<Role> list = roleService.queryAll(role);
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
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@OpertionBLog(title = "新增角色")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
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
	 * 分配角色对应的权限
	 * 
	 * @param roleRelationRightVO
	 * @return
	 */
	@OpertionBLog(title = "角色分配权限")
	@RequestMapping(value = "saveRelationRight", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult saveRelationRight(
			@RequestBody RolePermissionVO roleRelationRightVO) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.saveRelationRight(roleRelationRightVO);
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
	@ResponseBody
	@Requestauthorize
	public ResponseResult saveRelationDataScope(
			@RequestBody RoleDataScopeVO vo) {
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

	/**
	 * 根据id获取角色信息
	 * 
	 * @param code
	 * @return
	 */
	@OpertionBLog(title = "获取角色信息")
	@RequestMapping(value = "toModify/{code}")
	@ResponseBody
	public ResponseResult toModify(@PathVariable("code") Integer code) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(roleService.query(code));
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
	@OpertionBLog(title = "修改角色")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	@Requestauthorize
	public ResponseResult modify(@RequestBody Role role) {
		ResponseResult rep = new ResponseResult();
		try {
			roleService.modify(role);
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
	@OpertionBLog(title = "删除角色")
	@RequestMapping(value = "remove/{id}")
	@ResponseBody
	public ResponseResult remove(@PathVariable("id") Integer id) {
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

}
