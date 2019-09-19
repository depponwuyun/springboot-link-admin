package com.springboot.bcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.link.tool.bean.BeanUtils;
import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.common.exception.AuthException;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public List<Department> queryAll() {
		return departmentDao.selectAll();
	}

	@Override
	public Department query(Integer id) {
		Department dept = departmentDao.selectOne(id);
		return dept;
	}

	@Override
	public boolean add(Department dept) {
		if (dept.getParentId() == null) {
			dept.setParentId(0);
		}
		dept.setDeleted(0);
		int result = departmentDao.insert(dept);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

	@Override
	public boolean update(Department dept) {
		Department deptInfo = departmentDao.selectOne(dept.getId());
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		BeanUtils.copyObject(deptInfo, dept);
		int result = departmentDao.update(deptInfo);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		Department deptInfo = departmentDao.selectOne(id);
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		deptInfo.setDeleted(1);
		int result = departmentDao.update(deptInfo);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

}
