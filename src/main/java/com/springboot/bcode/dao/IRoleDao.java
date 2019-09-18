package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RolePermission;
import com.springboot.core.web.mvc.JqGridPage;

public interface IRoleDao {
	JqGridPage<Role> selectPage(Role role);

	List<Role> selectByUserId(String userId);

	Role select(Integer id);

	List<Role> select(Role role);

	int [] insert(List<RolePermission> rpList);

	int insertRetrunId(Role role);
	
	int insert(Role role);

	int update(Role role);

	int delete(Role role);

	int delete(RolePermission roleRelationRight);

}
