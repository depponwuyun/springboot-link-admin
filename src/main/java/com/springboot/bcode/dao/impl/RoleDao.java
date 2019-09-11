package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.link.tool.lang.StringUtils;
import com.springboot.bcode.dao.IRoleDao;
import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RolePermission;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.JqGridPage;

@Repository
public class RoleDao extends BaseDaoImpl implements IRoleDao {
	@Override
	public JqGridPage<Role> selectPage(Role role) {
		int startResult = (role.getPage() - 1) * role.getRows()+ 1;;
		int endResule = (role.getPage() * role.getRows());

		StringBuilder sql = new StringBuilder();
		StringBuilder sqlOrderBy = new StringBuilder();
		sql.append("select * from t_web_role where 1=1 ");

		if (StringUtils.isNotBlank(role.getName())) {
			sql.append(" and name like '%").append(
					role.getName().trim() + "%' ");
		}

		if (StringUtils.isNotBlank(role.getSidx())) {
			if ((role.getSord().trim().equalsIgnoreCase("asc"))) {
				sqlOrderBy.append(" order by " + role.getSidx().split(" ")[0]
						+ " asc");
			} else {
				sqlOrderBy.append(" order by " + role.getSidx().split(" ")[0]
						+ " desc");
			}
		} else {
			sqlOrderBy.append(" order by id asc");
		}

		StringBuffer newsql = new StringBuffer();
		newsql.append("select * from(select row_number() over (" + sqlOrderBy
				+ ") rownumber,* from( ");
		newsql.append(sql);
		newsql.append(")a )b where rownumber between " + startResult + " and "
				+ endResule);

		List<Role> list = super.find(newsql.toString(), null, Role.class);
		List<Role> count = super.find(sql.toString(), null, Role.class);
		JqGridPage<Role> page = new JqGridPage<Role>(list, count.isEmpty() ? 0
				: count.size(), role.getRows(), role.getPage());
		return page;
	}

	@Override
	public List<Role> selectByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.id,r.name from t_web_user_role ur INNER JOIN t_web_role r on ur.role_id=r.id ");
		sql.append(" where  ur.user_id=? ");

		return super.find(sql.toString(), new Object[] { userId }, Role.class);
	}

	@Override
	public List<Role> find(Role role) {
		return super.find(role);
	}

	@Override
	public int insert(Role role) {
		return super.insert(role);
	}

	@Override
	public int update(Role role) {
		return super.update(role);
	}

	@Override
	public int delete(Role role) {
		return super.delete(role);
	}

	@Override
	public int insert(RolePermission roleRelationRight) {
		return super.insert(roleRelationRight);
	}

	@Override
	public int delete(RolePermission roleRelationRight) {
		return super.delete(roleRelationRight);
	}

	@Override
	public Role select(Integer id) {
		return super.findById(id, Role.class);
	}

}
