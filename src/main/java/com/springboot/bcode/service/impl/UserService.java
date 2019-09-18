package com.springboot.bcode.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.tool.bean.BeanUtils;
import com.link.tool.lang.StringUtils;
import com.link.tool.utils.MD5Utils;
import com.link.tool.utils.UUIDUtils;
import com.springboot.bcode.dao.IUserDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.ModifyPwdVO;
import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserInfoVO;
import com.springboot.bcode.domain.auth.UserRole;
import com.springboot.bcode.service.IDataScopeService;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.bcode.service.IPermissionService;
import com.springboot.bcode.service.IRoleService;
import com.springboot.bcode.service.IUserService;
import com.springboot.common.AppToken;
import com.springboot.common.GlobalUser;
import com.springboot.common.exception.AuthException;
import com.springboot.common.exception.SystemException;
import com.springboot.core.algorithm.DepartmentRecursion;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService rightService;
	@Autowired
	private IDataScopeService dataScopeService;
	@Autowired
	private IDepartmentService departmentService;

	@Override
	public String login(LoginVO vo) {
		validateLoginCodition(vo);
		String token = AppToken.generateToken();
		UserInfo userInfo = loginProcess(vo);
		userInfo.setToken(token);
		GlobalUser.setUserInfo(userInfo);
		return token;
	}

	private void validateLoginCodition(LoginVO vo) {
		if (vo == null) {
			throw new AuthException("登录失败");
		}
		if (StringUtils.isBlank(vo.getUsername())) {
			throw new AuthException("用户名不能为空");
		}
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new AuthException("密码不能为空");
		}
	}

	private UserInfo loginProcess(LoginVO vo) {
		UserInfo param = new UserInfo();
		param.setName(vo.getUsername());
		// 根据登陆名
		UserInfo user = userDao.find(param);
		if (user == null) {
			throw new AuthException("无效的用户名或密码");
		}
		String md5password = MD5Utils.getMD5Encoding(vo.getPassword());
		if (!user.getPassword().equals(md5password)) {
			throw new AuthException("无效的用户名或密码");
		}
		if (GlobalUser.user_unable.equals(user.getState())) {
			throw new AuthException("该账户已被禁用");
		}
		return user;
	}

	@Override
	public UserInfo info() {
		// 判断用户数据是否为空
		UserInfo user = GlobalUser.getUserInfo();
		if (user == null) {
			throw new AuthException("用户未登录");
		}
		// 获取当前用户的所属公司或医院
		Department company = DepartmentRecursion.findCompany(user.getDeptid());
		if (company == null) {
			throw new SystemException("未查询到当前用户的所属公司或医院");
		}
		user.setCompanyId(company.getId());
		user.setCompanyName(company.getName());

		// 获取当前用户的部门
		Department dept = departmentService.query(user.getDeptid());
		if (dept == null) {
			throw new SystemException("未查询到当前用户的部门");
		}
		user.setDeptName(dept.getName());
		List<Role> roles = roleService.queryByUser(user.getUid());
		if (roles == null || roles.isEmpty()) {
			throw new SystemException("当前用户未分配角色");
		}
		user.setRoles(roles);

		List<Permission> permissionList = rightService.queryOwnedRight(user
				.getUid());

		if (permissionList == null || permissionList.isEmpty()) {
			throw new SystemException("当前用户为的角色未分配权限");
		}
		// 用户菜单
		List<Permission> menus = new ArrayList<Permission>();
		// 用户拥有的功能权限
		List<Permission> permissions = new ArrayList<Permission>();
		for (Permission perm : permissionList) {
			if (perm.isMenu()) {
				menus.add(perm);
			}
			if (perm.isPermission()) {
				permissions.add(perm);
			}
		}
		// 用户数据权限
		/*
		 * List<DataScope> ownedDateScopeList = new ArrayList<DataScope>(); for
		 * (Role role : roles) { ownedDateScopeList
		 * .addAll(dataScopeService.queryByRole(role.getId())); } Set<DataScope>
		 * ownedDatasocpeSet = new HashSet<DataScope>(); for (DataScope
		 * dataScope : ownedDateScopeList) { ownedDatasocpeSet.add(dataScope); }
		 * ownedDateScopeList = new ArrayList<DataScope>(ownedDatasocpeSet);
		 */

		// 更新用户的权限
		// ownedMenuRightList = RightRecursion.recursion(ownedMenuRightList);
		user.setMenus(menus);
		user.setPermissions(permissions);
		// user.setOwnedDateScopeList(ownedDateScopeList);
		GlobalUser.setUserInfo(user);
		return user;
	}

	@Override
	public void modifyPwd(ModifyPwdVO vo) {

		if (vo == null || StringUtils.isBlank(vo.getOldPassword())
				|| StringUtils.isBlank(vo.getNewPassword())
				|| StringUtils.isBlank(vo.getConfirmNewPassword())) {
			throw new AuthException("必填项不能为空");
		}
		if (!vo.getNewPassword().equals(vo.getConfirmNewPassword())) {
			throw new AuthException("两次输入密码必须相同");
		}
		UserInfo user = GlobalUser.getUserInfo();

		if (!user.getPassword().equals(
				MD5Utils.getMD5Encoding(vo.getOldPassword()))) {
			throw new AuthException("原密码错误");
		}

		UserInfo userInfo = userDao.select(user.getUid());
		if (userInfo == null) {
			throw new AuthException("用户不存在");
		}
		userInfo.setPassword(MD5Utils.getMD5Encoding(vo.getNewPassword()));
		userDao.update(userInfo);
		// 更新内存中的密码
		GlobalUser.setUserInfo(userInfo);

	}

	@Override
	public JqGridPage<UserInfo> queryPage(UserInfo user) {
		if (user == null) {
			throw new AuthException("参数不能为空");
		}
		JqGridPage<UserInfo> page = userDao.selectPage(user);
		if (page.getRows() != null && !page.getRows().isEmpty()) {
			for (UserInfo userInfo : page.getRows()) {
				Department dept = DepartmentRecursion.findCompany(userInfo
						.getDeptid());
				if (dept != null) {
					userInfo.setCompanyName(dept.getName());
				}
				List<Role> roleList = roleService
						.queryByUser(userInfo.getUid());
				userInfo.setRoles(roleList);
			}
		}
		return page;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(UserInfoVO vo) throws AuthException {

		if (StringUtils.isBlank(vo.getVserName())) {
			throw new AuthException("真实姓名不能为空");
		}
		if (StringUtils.isBlank(vo.getName())) {
			throw new AuthException("登陆名不能为空");
		}
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new AuthException("登陆密码不能为空");
		}
		if (vo.getRoleIds() == null || vo.getRoleIds().length == 0) {
			throw new AuthException("请分配角色");
		}

		UserInfo user = new UserInfo();
		BeanUtils.copyObject(user, vo);
		user.setCreateTime(new Date());
		String password = MD5Utils.getMD5Encoding(user.getPassword());
		user.setPassword(password);
		user.setUid(UUIDUtils.generateUUID());
		// 保存用户信息
		int result = userDao.insert(user);
		if (result < 0) {
			throw new AuthException("保存失败");
		}

		List<UserRole> urList = new ArrayList<UserRole>();
		UserRole ur;
		for (Integer roleId : vo.getRoleIds()) {
			ur = new UserRole();
			ur.setUserId(user.getUid());
			ur.setRoleId(roleId);
			urList.add(ur);
		}
		// 保存用户分配的角色
		saveRelationRole(urList);
		return true;
	}

	@Override
	public boolean modify(UserInfoVO vo) throws AuthException {

		if (vo.getUid() == null) {
			throw new AuthException("用户不存在");
		}
		if (vo.getRoleIds() == null || vo.getRoleIds().length == 0) {
			throw new AuthException("请分配角色");
		}

		UserInfo user = userDao.select(vo.getUid());
		if (user == null) {
			throw new AuthException("用户不存在");
		}
		String password = "";
		if (!user.getPassword().equals(vo.getPassword())) {
			password = MD5Utils.getMD5Encoding(vo.getPassword());
		}
		BeanUtils.copyObject(user, vo);
		if (StringUtils.isNotBlank(password)) {
			user.setPassword(password);
		}
		int result = userDao.update(user);
		if (result < 0) {
			throw new AuthException("修改失败");
		}

		// 删除用户角色
		UserRole delUr = new UserRole();
		delUr.setUserId(user.getUid());
		userDao.delete(delUr);

		List<UserRole> urList = new ArrayList<UserRole>();
		UserRole ur;
		for (Integer roleId : vo.getRoleIds()) {
			ur = new UserRole();
			ur.setUserId(user.getUid());
			ur.setRoleId(roleId);
			urList.add(ur);
		}
		// 保存用户分配的角色
		saveRelationRole(urList);
		return true;

	}

	public boolean saveRelationRole(List<UserRole> urList) {
		userDao.insert(urList);
		return true;
	}

	@Override
	public boolean remove(String uid) throws AuthException {
		if (StringUtils.isBlank(uid)) {
			throw new AuthException("uid不能为空");
		}
		UserInfo user = new UserInfo();
		user.setUid(uid);

		int result = userDao.delete(user);
		if (result < 0) {
			throw new AuthException("删除失败");
		}
		return true;
	}

	@Override
	public UserInfo find(String uid) {
		if (StringUtils.isBlank(uid)) {
			return null;
		}
		UserInfo userInfo = userDao.select(uid);
		return userInfo;
	}

	@Override
	public UserInfo find(UserInfo user) {
		return userDao.find(user);
	}

}
