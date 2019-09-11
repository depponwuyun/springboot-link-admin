package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.Permission;

public interface IRightDao {
	Permission select(Integer id);

	List<Permission> selectAll();

	List<Permission> selectByRoleCode(Integer roleCode);

	List<Permission> find(Permission right);

	List<Permission> findChild(Integer parentCode);

	int insert(Permission right);

	int update(Permission right);

	int delete(Permission right);

}
