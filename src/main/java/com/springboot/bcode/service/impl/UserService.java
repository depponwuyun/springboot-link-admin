package com.springboot.bcode.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.tool.lang.StringUtils;
import com.link.tool.utils.MD5Utils;
import com.springboot.bcode.dao.IUserDao;
import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.bcode.domain.auth.LoginVO;
import com.springboot.bcode.domain.auth.ModifyPwdVO;
import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.UserInfo;
import com.springboot.bcode.domain.auth.UserRole;
import com.springboot.bcode.domain.auth.UserRoleVO;
import com.springboot.bcode.service.IDataScopeService;
import com.springboot.bcode.service.IDepartmentService;
import com.springboot.bcode.service.IRightService;
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
	private IRightService rightService;
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
		List<Role> roles = roleService.queryOwnedRole(user.getUid());
		if (roles == null || roles.isEmpty()) {
			throw new SystemException("当前用户未分配角色");
		}
		user.setRoles(roles);
		String roleName = "";
		for (Role role : roles) {
			roleName += role.getName() + ";";
		}
		user.setRoleName(roleName);
		List<Permission> ownedRightList = rightService.queryOwnedRight(user
				.getUid());

		if (ownedRightList == null || ownedRightList.isEmpty()) {
			throw new SystemException("当前用户为的角色未分配权限");
		}
		// 用户菜单
		List<Permission> ownedMenuRightList = new ArrayList<Permission>();
		// 用户拥有的功能权限
		List<Permission> ownedFunctionList = new ArrayList<Permission>();
		for (Permission right : ownedRightList) {
			if (right.isMenu()) {
				ownedMenuRightList.add(right);
			}
			if (right.isFunction()) {
				ownedFunctionList.add(right);
			}
		}
		// 用户数据权限
		List<DataScope> ownedDateScopeList = new ArrayList<DataScope>();
		for (Role role : roles) {
			ownedDateScopeList
					.addAll(dataScopeService.queryByRole(role.getId()));
		}
		Set<DataScope> ownedDatasocpeSet = new HashSet<DataScope>();
		for (DataScope dataScope : ownedDateScopeList) {
			ownedDatasocpeSet.add(dataScope);
		}
		ownedDateScopeList = new ArrayList<DataScope>(ownedDatasocpeSet);

		// 更新用户的权限
		// ownedMenuRightList = RightRecursion.recursion(ownedMenuRightList);
		user.setMenus(ownedMenuRightList);
		user.setPermissions(ownedFunctionList);
		user.setOwnedDateScopeList(ownedDateScopeList);
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
		//更新内存中的密码
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
				List<Role> roleList = roleService.queryOwnedRole(userInfo
						.getUid());
				if (roleList != null && !roleList.isEmpty()) {
					String roleName = "";
					for (Role role : roleList) {
						roleName += role.getName() + ";";
					}
					userInfo.setRoleName(roleName);
				}
			}
		}
		return page;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(UserInfo user) throws AuthException {
		if (user == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (StringUtils.isBlank(user.getVserName())) {
			throw new AuthException("真实姓名不能为空");
		}
		if (StringUtils.isBlank(user.getName())) {
			throw new AuthException("登陆名不能为空");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new AuthException("登陆密码不能为空");
		}
		// user.setUid(UUIDUtils.generateUUID());
		user.setCreateTime(new Date());
		String password = MD5Utils.getMD5Encoding(user.getPassword());
		user.setPassword(password);
		int result = userDao.insert(user);
		if (result < 0) {
			throw new AuthException("保存失败");
		}
		return true;
	}

	@Override
	public boolean modify(UserInfo user) throws AuthException {
		if (user == null) {
			throw new AuthException("修改数据不能为空");
		}
		if (user.getUid() == null) {
			throw new AuthException("userId不能为空");
		}
		UserInfo oldUser = userDao.select(user.getUid());
		if (oldUser == null) {
			throw new AuthException("userInfo不存在");
		}
		if (!oldUser.getName().equals(user.getName())) {
			throw new AuthException("登陆账号不能修改");
		}
		if (!oldUser.getPassword().equals(user.getPassword())) {
			String password = MD5Utils.getMD5Encoding(user.getPassword());
			user.setPassword(password);
		}
		int result = userDao.update(user);
		if (result < 0) {
			throw new AuthException("修改失败");
		}
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

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean saveRelationRole(UserRoleVO vo) {
		if (vo == null) {
			throw new AuthException("修改数据不能为空");
		}
		if (vo.getUserId() == null) {
			throw new AuthException("请先选择用户");
		}
		if (vo.getRoleId() == null) {
			throw new AuthException("请选择角色");
		}
		UserRole del = new UserRole();
		del.setUserId(vo.getUserId());
		userDao.delete(del);

		List<UserRole> list = new ArrayList<UserRole>();
		UserRole relationRole;
		for (String roleId : vo.getRoleId()) {
			relationRole = new UserRole();
			relationRole.setUserId(vo.getUserId());
			relationRole.setRoleId(Integer.parseInt(roleId));
			list.add(relationRole);
		}
		userDao.insert(list);
		return true;
	}

	@Override
	public UserInfo find(UserInfo user) {
		return userDao.find(user);
	}

}
