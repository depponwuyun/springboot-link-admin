package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.bcode.dao.IDataScopeDao;
import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.core.jdbc.BaseDaoImpl;

@Repository
public class DataScopeDao extends BaseDaoImpl implements IDataScopeDao {

	@Override
	public int insert(DataScope dataScope) {
		return super.insert(dataScope);
	}

	@Override
	public int delete(DataScope dataScope) {
		return super.delete(dataScope);
	}

	@Override
	public List<DataScope> select(DataScope dataScope) {
		return super.select(dataScope);
	}

	@Override
	public List<DataScope> selectRole(Integer roleId) {
		String sql = "SELECT id,role_id as roleId, targetCategory ,targetId,targetName,targetUrl,permissionId  from t_web_datascope where role_id='"
				+ roleId + "'";
		return super.select(sql, DataScope.class);
	}
}