package com.springboot.core.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.link.tool.bean.BeanUtils;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.common.LocalCache;
import com.springboot.common.constant.Constant;

public class DepartmentRecursion {
	/**
	 * 
	 * 根据部门id查找所有下级部门(包含自己部门)
	 * 
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 *
	 */
	public static List<Department> findSelfAndAllChild(Integer id) {
		if (id == null) {
			return null;
		}
		List<Department> allList = LocalCache.get(Constant.caceh_all_dept_key);
		if (allList == null || allList.isEmpty() || id == null) {
			return null;
		}
		List<Department> resultList = new ArrayList<Department>();
		Department dept = new Department();
		for (Department department : allList) {
			if (department.getId().equals(id)) {
				dept = department;
				resultList.add(department);
				break;
			}
		}
		findAllChild(allList, resultList, dept);
		return resultList;
	}

	/**
	 * 根据部门id查找所有下级部门
	 *
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 *
	 */
	public static List<Department> findAllChild(Integer id) {
		if (id == null) {
			return null;
		}
		List<Department> allList = LocalCache.get(Constant.caceh_all_dept_key);
		if (allList == null || allList.isEmpty() || id == null) {
			return null;
		}
		List<Department> resultList = new ArrayList<Department>();
		Department dept = new Department();
		dept.setId(id);
		findAllChild(allList, resultList, dept);
		return resultList;
	}

	/**
	 * 根据部门id，递归查询下级所有子部门
	 * 
	 * @param allList
	 * @param resultList
	 * @param obj
	 */
	private static void findAllChild(List<Department> allList,
			List<Department> resultList, Department obj) {
		// final修饰，引用不可变，只能进行赋值判断
		for (Department dept : allList) {
			if (dept.getParentId().equals(obj.getId())) {
				resultList.add(dept);
				findAllChild(allList, resultList, dept);
			}
		}
	}

	/**
	 * 根据部门id查询公司
	 * 
	 * @param id
	 * @return
	 */
	public static Department findCompany(Integer id) {
		List<Department> allList = LocalCache.get(Constant.caceh_all_dept_key);
		if (allList == null || allList.isEmpty() || id == null) {
			return null;
		}
		Department target = new Department();
		findCompany(allList, id, target);
		return target;
	}

	private static void findCompany(List<Department> allList, Integer parentId,
			Department target) {
		for (Department dept : allList) {
			if (dept.getId().equals(parentId)) {
				if (dept.getLevels().equals(1) || dept.getLevels().equals(0)) {
					BeanUtils.copyObject(target, dept);
					break;
				}
				findCompany(allList, dept.getParentId(), target);
			}
		}
	}

}
