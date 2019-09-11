package com.springboot.bcode.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.logger.OpertionBLog;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

@Controller
@RequestMapping(value = "/dept")
public class DepartmentRest extends BaseRest {
	@Autowired
	private IDepartmentService departmentInfoService;

	@RequestMapping("manager")
	public String toUserManage() {
		return "auth/dept_manager";
	}

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryAll")
	@ResponseBody
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			List<Department> list = departmentInfoService.queryAll();
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

	@RequestMapping(value = "queryAllHospital")
	@ResponseBody
	public ResponseResult queryAllHospital() {
		ResponseResult rep = new ResponseResult();
		try {
			List<Department> list = departmentInfoService.queryAllHospital();
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
	 * 根据上级id查询所有下级部门
	 *
	 * @param @param id
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "queryAllChild")
	@ResponseBody
	public ResponseResult queryAllChild(
			@RequestParam(value = "id", required = false) Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			List<Department> list = departmentInfoService.queryAllChild(id);
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
	 * 部门管理用的
	 * 根据上级部门id获取下级直接部门(只包含直接子部门)
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
			List<Department> list = departmentInfoService.queryChild(parentId);
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
	 * 保存部门
	 * 
	 * @param dept
	 * @return
	 */
	@OpertionBLog(title = "新增部门")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult save(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentInfoService.save(dept);
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
	 * 根据id获取部门信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "query/{id}")
	@ResponseBody
	public ResponseResult toModify(@PathVariable("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(departmentInfoService.query(id));
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
	 * 修改
	 * 
	 * @param dept
	 * @return
	 */
	@OpertionBLog(title = "修改部门")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult modify(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentInfoService.modify(dept);
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
	 * 移除
	 * 
	 * @param id
	 * @return
	 */
	@OpertionBLog(title = "删除部门")
	@RequestMapping(value = "remove/{id}")
	@ResponseBody
	public ResponseResult remove(@PathVariable("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentInfoService.remove(id);
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
