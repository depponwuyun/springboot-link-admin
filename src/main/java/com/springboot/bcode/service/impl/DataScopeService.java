package com.springboot.bcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bcode.dao.IDataScopeDao;
import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.service.IDataScopeService;
import com.springboot.common.exception.AuthException;

@Service
public class DataScopeService implements IDataScopeService {

	@Autowired
	private IDataScopeDao dataScopeDao;
	@Autowired
	private IDepartmentDao departmentInfoDao;

	@Override
	public List<DataScope> queryByRole(Integer roleId) {
		return dataScopeDao.selectRole(roleId);
	}

	@Override
	public List<Department> queryAllCheckByRole(DataScope ds)
			throws AuthException {
		List<Department> deptList = departmentInfoDao.selectAll();
		if (deptList == null || deptList.isEmpty()) {
			throw new AuthException("未查询到数据权限");
		}

		List<DataScope> dsList = dataScopeDao.select(ds);
		if (dsList != null && !dsList.isEmpty()) {
			for (Department department : deptList) {
				for (DataScope dataScope : dsList) {
				}
			}
		}

		return deptList;
	}
}
