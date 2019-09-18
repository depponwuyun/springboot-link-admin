package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.Permission;

public interface IPermissionDao {
	Permission select(Integer id);

	List<Permission> selectAll();

	List<Permission> selectByRole(String  roleIds);

	List<Permission> find(Permission right);

	List<Permission> findChild(Integer parentCode);

	int insert(Permission right);

	int update(Permission right);

	int delete(Permission right);

}
