package com.springboot.bcode.domain.auth;

import java.util.Date;
import java.util.List;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
import com.springboot.core.web.mvc.JqGridParam;

@Tables(table = "t_web_user")
public class UserInfo extends JqGridParam {

	@Columns(column = "uid", primaryKey = true)
	private String uid;
	@Columns(column = "name")
	private String name;
	@Columns(column = "password")
	private String password;
	@Columns(column = "vsername")
	private String vserName;
	@Columns(column = "mobile")
	private String mobile;
	@Columns(column = "createTime")
	private Date createTime;
	@Columns(column = "state")
	private Integer state;// 用户状态1:正常;0:禁用
	@Columns(column = "deptid")
	private Integer deptid;// 部门id
	@Columns(column = "email")
	private String email;
	private String avatar="pic";
	private String introduction="hello word";
	
	
	private String token;
	private String deptName;// 部门名称
	private Integer companyId;// 公司id
	private String companyName;// 部门对应的总节点部门（总公司，医院，集团）
	private String roleName;// 角色名称
	//角色
	private List<Role> roles;

	// 用户的菜单
	private List<Permission> menus;
	// 用户拥有的功能权限
	private List<Permission> permissions;
	// 用户拥有的数据权限
	private List<DataScope> ownedDateScopeList;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVserName() {
		return vserName;
	}

	public void setVserName(String vserName) {
		this.vserName = vserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<DataScope> getOwnedDateScopeList() {
		return ownedDateScopeList;
	}

	public void setOwnedDateScopeList(List<DataScope> ownedDateScopeList) {
		this.ownedDateScopeList = ownedDateScopeList;
	}


	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getMenus() {
		return menus;
	}

	public void setMenus(List<Permission> menus) {
		this.menus = menus;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
