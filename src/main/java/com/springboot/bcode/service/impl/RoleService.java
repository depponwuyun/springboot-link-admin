package com.springboot.bcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.tool.bean.BeanUtils;
import com.link.tool.lang.StringUtils;
import com.springboot.bcode.dao.IDataScopeDao;
import com.springboot.bcode.dao.IRightDao;
import com.springboot.bcode.dao.IRoleDao;
import com.springboot.bcode.dao.IUserDao;
import com.springboot.bcode.domain.auth.DataScope;
import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RoleDataScopeVO;
import com.springboot.bcode.domain.auth.RolePermission;
import com.springboot.bcode.domain.auth.RolePermissionVO;
import com.springboot.bcode.service.IRoleService;
import com.springboot.common.exception.AuthException;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRightDao rightDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IDataScopeDao dataScopeDao;

	@Override
	public JqGridPage<Role> queryPage(Role role) {
		return roleDao.selectPage(role);
	}

	@Override
	public List<Role> queryAll(Role role) throws AuthException {
		return roleDao.find(role);
	}

	@Override
	public List<Role> queryOwnedRole(String userId) throws AuthException {
		if (StringUtils.isBlank(userId)) {
			throw new AuthException("userId不能为空");
		}
		return roleDao.selectByUserId(userId);
	}

	@Override
	public boolean add(Role right) throws AuthException {
		if (right == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (StringUtils.isBlank(right.getName())) {
			throw new AuthException("name不能为空");
		}
		int result = roleDao.insert(right);
		if (result < 0) {
			throw new AuthException("执行失败");
		}
		return true;
	}

	@Override
	public boolean modify(Role role) throws AuthException {
		Role rightInfo = roleDao.select(role.getId());
		if (rightInfo == null) {
			throw new AuthException("未查询到角色信息");
		}
		BeanUtils.copyObject(rightInfo, role);
		int result = roleDao.update(rightInfo);
		if (result < 0) {
			throw new AuthException("执行失败");
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean delete(Integer code) throws AuthException {
		if (code == null) {
			throw new AuthException("删除条件不能为空");
		}
		Role role = new Role();
		role.setId(code);
		int result = 0;
		// 删除角色本身
		result = roleDao.delete(role);
		if (result < 0) {
			throw new AuthException("执行失败");
		}

		// 删除角色对应的权限
		RolePermission roleRight = new RolePermission();
		roleRight.setRoleId(code);
		roleDao.delete(roleRight);

		return true;
	}

	@Override
	public Role query(Integer code) throws AuthException {

		Role role = new Role();
		role.setId(code);
		List<Role> list = roleDao.find(role);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean saveRelationRight(RolePermissionVO relationRightVO)
			throws AuthException {

		if (relationRightVO == null) {
			throw new AuthException("条件不能为空");
		}
		if (relationRightVO.getRightIdList() == null
				|| relationRightVO.getRightIdList().isEmpty()) {
			throw new AuthException("条件不能为空");
		}
		if (relationRightVO.getRoleId() == null) {
			throw new AuthException("条件不能为空");
		}
		// 删除角色对应的权限 RoleRelationRight
		RolePermission roleRelationRight = new RolePermission();
		roleRelationRight.setRoleId(relationRightVO.getRoleId());
		roleDao.delete(roleRelationRight);

		// 循环插入角色对应的权限
		for (Integer rightId : relationRightVO.getRightIdList()) {
			roleRelationRight.setRightId(rightId);
			roleDao.insert(roleRelationRight);
		}

		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean saveRelationDataScope(RoleDataScopeVO vo) {
		if (vo == null) {
			throw new AuthException("条件不能为空");
		}
		if (vo.getRoleId() == null) {
			throw new AuthException("条件不能为空");
		}
		// 删除角色、模块对应的数据权限
		DataScope ds = new DataScope();
		ds.setRoleId(vo.getRoleId());
		ds.setTargetUrl(vo.getTargetUrl());
		ds.setTargetId(vo.getTargetId());
		dataScopeDao.delete(ds);

		if (vo.getDataScopeIdList() != null
				&& !vo.getDataScopeIdList().isEmpty()) {
			// 循环插入角色对应的数据权限
			for (String permissionId : vo.getDataScopeIdList()) {
				ds.setTargetUrl(vo.getTargetUrl());
				ds.setPermissionId(permissionId);
				ds.setTargetCategory(vo.getTargetCategory());
				ds.setTargetId(vo.getTargetId());
				ds.setTargetName(vo.getTargetName());
				dataScopeDao.insert(ds);
			}
		}
		return true;
	}

}
