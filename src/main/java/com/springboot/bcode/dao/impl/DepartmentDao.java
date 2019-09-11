package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.core.jdbc.BaseDaoImpl;

@Repository
public class DepartmentDao extends BaseDaoImpl implements IDepartmentDao {

	@Override
	public List<Department> selectAll() {
		String sql = "SELECT id,name,  parent_id as parentId,levels,for_service as forService,sorts from t_web_dept r where deleted=0  order by sorts asc ";
		return super.find(sql, new Object[] {}, Department.class);
	}

	@Override
	public Department selectOne(Integer id) {
		return super.findById(id, Department.class);
	}

	@Override
	public List<Department> findChild(Integer parentId) {
		String sql = "SELECT id,name,  parent_id as parentId,levels,for_service as forService,sorts,(SELECT top 1 tmp1.name  FROM t_web_dept as tmp1 WHERE tmp1.parent_id=r.id )  as tmpChildName from t_web_dept r where r.parent_id=? order by sorts asc ";
		return super.find(sql, new Object[] { parentId }, Department.class);
	}

	@Override
	public int insert(Department dept) {
		// TODO Auto-generated method stub
		return super.insert(dept);
	}

	@Override
	public int update(Department dept) {
		// TODO Auto-generated method stub
		return super.update(dept);
	}

}
