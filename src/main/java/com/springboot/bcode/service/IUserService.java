package com.springboot.bcode.service;


import javax.servlet.http.HttpSession;

import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserRoleVO;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.JqGridPage;

public interface IUserService {

	JqGridPage<UserInfo> queryPage(UserInfo user);

	String login(LoginVO vo) ;
	
	UserInfo info();

	UserInfo find(String uid);

	UserInfo find(UserInfo user);


	void modifyPwd(HttpSession session, String newPassword,
			String enNewpassword, String oldPassword);

	boolean add(UserInfo user) throws AuthException;

	boolean saveRelationRole(UserRoleVO vo);

	boolean modify(UserInfo user) throws AuthException;

	boolean remove(String uid) throws AuthException;

}
