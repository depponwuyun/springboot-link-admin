package com.springboot.bcode.domain.auth;

import java.util.List;

public class RoleDataScopeVO {

	private Integer roleId;

	private List<String> dataScopeIdList;

	private String targetCategory;
	private String targetId;
	private String targetName;
	private String targetUrl;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<String> getDataScopeIdList() {
		return dataScopeIdList;
	}

	public void setDataScopeIdList(List<String> dataScopeIdList) {
		this.dataScopeIdList = dataScopeIdList;
	}

	public String getTargetCategory() {
		return targetCategory;
	}

	public void setTargetCategory(String targetCategory) {
		this.targetCategory = targetCategory;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

}
