package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.DataScope;

public interface IDataScopeDao {
	List<DataScope> selectRole(Integer roleId);

	List<DataScope> select(DataScope dataScope);

	int insert(DataScope dataScope);

	int delete(DataScope dataScope);

}
