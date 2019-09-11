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
	private IDepartmentDao departmentInfoDao;

	@Override
	public List<Department> queryAll() {
		// 优先从内存中读取，没有从数据库读取
		List<Department> allDept = LocalCache.get(Constant.caceh_all_dept_key);
		if (allDept == null || allDept.isEmpty()) {
			allDept = departmentInfoDao.selectAll();
		}
		return allDept;
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
		Department dept = departmentInfoDao.selectOne(id);
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
			dept.setIsParent(true);
			list.add(dept);
		} else {
			list = departmentInfoDao.findChild(id);
			if (list != null && !list.isEmpty()) {
				for (Department right : list) {
					if (StringUtils.isNotBlank(right.getTmpChildName())) {
						right.setIsParent(true);
					}
				}
			}
		}
		return list;
	}

	@Override
	public boolean save(Department dept) {
		if (dept.getParentId() == null) {
			dept.setParentId(0);
		}
		dept.setDeleted(0);
		int result = departmentInfoDao.insert(dept);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

	@Override
	public boolean modify(Department dept) {
		Department deptInfo = departmentInfoDao.selectOne(dept.getId());
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		BeanUtils.copyObject(deptInfo, dept);
		int result = departmentInfoDao.update(deptInfo);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

	@Override
	public boolean remove(Integer id) {
		Department deptInfo = departmentInfoDao.selectOne(id);
		if (deptInfo == null) {
			throw new AuthException("未查询到部门信息");
		}
		deptInfo.setDeleted(1);
		int result = departmentInfoDao.update(deptInfo);
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
