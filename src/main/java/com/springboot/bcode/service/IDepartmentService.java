package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Department;

public interface IDepartmentService {
	List<Department> queryAll();

	
	/**
	 * 查询所有医院
	 * 
	 * @return
	 */
	List<Department> queryAllHospital();
    /**
     * 查询部门信息
     * @param id
     * @return
     */
	Department query(Integer id);
	/**
	 * 查询所有下级部门
	 */
	List<Department> queryAllChild(Integer id);
	/**
	 * 查询下级部门（只包含一级）
	 */
	List<Department> queryChild(Integer id);

	/**
	 * 查询下级部门id（包含下级所有级别）
	 * 
	 * @param parentId
	 * @return
	 */
	List<Integer> queryChildId(Integer id);

	/**
	 * 根据id查询子公司
	 * 
	 * @param id
	 * @return
	 */
	Department queryCompany(Integer id);

	boolean save(Department dept);

	boolean modify(Department dept);

	boolean remove(Integer id);
}
