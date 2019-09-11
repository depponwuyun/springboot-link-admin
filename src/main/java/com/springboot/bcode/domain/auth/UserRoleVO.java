package com.springboot.bcode.domain.auth;

import com.link.tool.lang.StringUtils;

public class UserRoleVO {
	private String userId;
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String [] getRoleId() {
		String[] roleIdArr={};
		if(StringUtils.isNotBlank(roleId)){
			roleIdArr=roleId.split(";");
		}
		return roleIdArr;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}
