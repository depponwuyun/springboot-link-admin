package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Permission;
import com.springboot.common.exception.AuthException;


public interface IRightService {

	List<Permission> queryAll(Permission right) throws AuthException;

	List<Permission> queryChild(Integer parentCode) throws AuthException;

	List<Permission> queryOwnedRight(String userId);

	List<Permission> queryAllCheckByRole(Integer roleCode) throws AuthException;

	Permission query(Integer code) throws AuthException;

	boolean save(Permission right) throws AuthException;

	boolean modify(Permission right) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
