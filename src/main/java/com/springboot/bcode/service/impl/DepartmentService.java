package com.springboot.bcode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.link.tool.bean.BeanUtils;
import com.link.tool.lang.StringUtils;
import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.common.LocalCache;
import com.springboot.common.constant.Constant;
import com.springboot.common.exception.AuthException;
import com.springboot.core.algorithm.DepartmentRecursion;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public List<Department> queryAll() {
		return departmentDao.selectAll();
	}

	/**
	 * 根据id查询子公司
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Department queryCompany(Integer id) {
		Department dept = DepartmentRecursion.findCompany(id);
		return dept;
	}

	@Override
	public Department query(Integer id) {
		Department dept = departmentDao.selectOne(id);
		return dept;
	}

	/**
	 * 查询下级部门（只包含一级）
	 */
	@Override
	public List<Department> queryChild(Integer id) {
		List<Department> list = new ArrayList<Department>();
		if (id == null) {
			Department dept = new Department();
			dept.setId(0);
			dept.setName("部门管理树");
			dept.setParentId(0);
			list.add(dept);
		} else {
			list = departmentDao.findChild(id);
		}
		return list;
	}

	@Override
	public boolean save(Department dept) {
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
	public boolean modify(Department dept) {
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
	public boolean remove(Integer id) {
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

	/**
	 * 查询下级部门id（包含下级所有级别）
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<Integer> queryChildId(Integer id) {
		List<Department> deptList = queryAll();
		if (deptList == null || deptList.isEmpty()) {
			return null;
		}
		List<Department> resultList = DepartmentRecursion.findAllChild(id);
		List<Integer> idList = new ArrayList<Integer>();
		for (Department dept : resultList) {
			idList.add(dept.getId());
		}
		return idList;
	}

	@Override
	public List<Department> queryAllHospital() {
		List<Department> hostpitalList = new ArrayList<Department>();
		List<Department> allDeptList = queryAll();
		for (Department department : allDeptList) {
			if (department.getLevels() == 1) {
				hostpitalList.add(department);
			}
		}
		return hostpitalList;
	}

	
	@Override
	public List<Department> queryAllChild(Integer id) {
		List<Department> deptList=new ArrayList<Department>();
		List<Department> list=	DepartmentRecursion.findAllChild(id);
		if(list!=null && !list.isEmpty()){
			for (Department dept : list) {
				if (dept.getForService()==1) {
					deptList.add(dept);
				}
			}
		}
		return deptList;
	}

}
