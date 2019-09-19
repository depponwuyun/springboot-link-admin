package com.springboot.bcode.service;

import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.ModifyPwdVO;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserInfoVO;
import com.springboot.core.web.mvc.JqGridPage;

public interface IUserService {

	JqGridPage<UserInfo> queryPage(UserInfo user);

	String login(LoginVO vo);

	UserInfo info();

	UserInfo find(String uid);

	UserInfo find(UserInfo user);

	void modifyPwd(ModifyPwdVO vo);

	boolean add(UserInfoVO user);

	boolean update(UserInfoVO user);

	boolean delete(String uid);

}
