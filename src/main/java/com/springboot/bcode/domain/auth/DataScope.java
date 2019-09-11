package com.springboot.bcode.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

/**
 * 数据权限实体类
 * 
 * @author Administrator
 *
 */
@Tables(table = "t_web_datascope")
public class DataScope {
	// id int NOT NULL IDENTITY(1,1), --id
	// role_id int, --角色id
	// targetCategory varchar(30) , --基础数据 数据权限类别
	// targetId varchar(32), --基础数据
	// targetName varchar(30) ,--基础数据
	// targetUrl varchar(150) NOT NULL-基础数据, 根据查询模块请求url获取数据权限
	// permissionId varchar(30) --权限域
	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "role_id")
	private Integer roleId;
	@Columns(column = "targetCategory")
	private String targetCategory;
	@Columns(column = "targetId")
	private String targetId;
	@Columns(column = "targetName")
	private String targetName;
	@Columns(column = "targetUrl")
	private String targetUrl;
	@Columns(column = "permissionId")
	private String permissionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permissionId == null) ? 0 : permissionId.hashCode());
		result = prime * result
				+ ((targetId == null) ? 0 : targetId.hashCode());
		result = prime * result
				+ ((targetUrl == null) ? 0 : targetUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataScope other = (DataScope) obj;
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		if (targetUrl == null) {
			if (other.targetUrl != null)
				return false;
		} else if (!targetUrl.equals(other.targetUrl))
			return false;
		return true;
	}

}
