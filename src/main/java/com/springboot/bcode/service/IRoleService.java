package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RoleDataScopeVO;
import com.springboot.bcode.domain.auth.RolePermissionVO;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.JqGridPage;

public interface IRoleService {

	JqGridPage<Role> queryPage(Role role);

	List<Role> queryAll(Role role) throws AuthException;

	List<Role> queryOwnedRole(String userId) throws AuthException;

	Role query(Integer code) throws AuthException;

	boolean add(Role role) throws AuthException;

	boolean saveRelationRight(RolePermissionVO vo) throws AuthException;

	boolean saveRelationDataScope(RoleDataScopeVO vo);

	boolean modify(Role role) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
