package com.springboot.bcode.domain.auth;

import java.util.List;

public class RolePermissionVO {

	private Integer roleId;

	private List<Integer> rightIdList;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getRightIdList() {
		return rightIdList;
	}

	public void setRightIdList(List<Integer> rightIdList) {
		this.rightIdList = rightIdList;
	}

}
