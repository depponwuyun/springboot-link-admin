package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Department;

public interface IDepartmentService {
	List<Department> queryAll();

	/**
	 * 查询部门信息
	 * 
	 * @param id
	 * @return
	 */
	Department query(Integer id);

	boolean add(Department dept);

	boolean update(Department dept);

	boolean delete(Integer id);
}
