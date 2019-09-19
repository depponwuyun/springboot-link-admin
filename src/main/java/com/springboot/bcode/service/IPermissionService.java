package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Permission;
import com.springboot.common.exception.AuthException;


public interface IPermissionService {

	List<Permission> queryAll();


	List<Permission> queryOwnedRight(String userId);

	List<Permission> queryAllByRole(String roleIds);

	Permission query(Integer code) throws AuthException;

	boolean add(Permission right) throws AuthException;

	boolean update(Permission right) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
