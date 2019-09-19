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

	int insert(UserInfo user);

	int[] insert(List<UserRole> list);

	int update(UserInfo user);

	int updateStateByOrgCode(String orgCode, Integer state);

	int delete(UserInfo user);

	int delete(UserRole userRelationRole);

}
