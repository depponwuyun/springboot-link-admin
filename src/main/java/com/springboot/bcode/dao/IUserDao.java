package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserRole;
import com.springboot.core.web.mvc.JqGridPage;

public interface IUserDao {

	JqGridPage<UserInfo> selectPage(UserInfo user);

	UserInfo find(UserInfo user);

	List<UserInfo> findList(UserInfo user);

	UserInfo select(String id);

	/**
	 * 查询部门下面所有的用户
	 *
	 * @param @param deptId
	 * @param @return 设定文件
	 * @return List<UserInfo> 返回类型
	 *
	 */
	List<UserInfo> selectByDeptBelow(Integer deptId);

	int insert(UserInfo user);

	int[] insert(List<UserRole> list);

	int update(UserInfo user);

	int updateStateByOrgCode(String orgCode, Integer state);

	int delete(UserInfo user);

	int delete(UserRole userRelationRole);

}
