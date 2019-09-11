package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.common.exception.AuthException;

public interface IDataScopeService {

	List<DataScope> queryByRole(Integer roleId);

	List<Department> queryAllCheckByRole(DataScope ds) throws AuthException;

}
