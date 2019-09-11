package com.springboot.bcode.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.tool.bean.BeanUtils;
import com.link.tool.lang.StringUtils;
import com.springboot.bcode.dao.IRightDao;
import com.springboot.bcode.dao.IRoleDao;
import com.springboot.bcode.dao.IUserDao;
import com.springboot.bcode.domain.auth.Permission;
import com.springboot.bcode.domain.auth.Role;
import com.springboot.bcode.domain.auth.RolePermission;
import com.springboot.bcode.service.IRightService;
import com.springboot.common.exception.AuthException;

@Service
public class RightService implements IRightService {

	@Autowired
	private IRightDao rightDao;
	@Autowired
	private IUserDao userInfoDao;
	@Autowired
	private IRoleDao roleDao;

	@Override
	public List<Permission> queryAll(Permission right) throws AuthException {
		return rightDao.find(right);
	}

	@Override
	public List<Permission> queryOwnedRight(String userId) {
		List<Permission> owendRightList = new ArrayList<Permission>();

		List<Role> owendRoleList = roleDao.selectByUserId(userId);
		if (owendRoleList != null && !owendRoleList.isEmpty()) {
			for (Role role : owendRoleList) {
				owendRightList.addAll(rightDao.selectByRoleCode(role.getId()));
			}
		}
		if (owendRightList == null || owendRightList.isEmpty()) {
			return null;
		}
		Set<Permission> ownedSet = new HashSet<Permission>();
		for (Permission right : owendRightList) {
			right.setOpen(true);
			ownedSet.add(right);
		}
		owendRightList = new ArrayList<Permission>(ownedSet);
		return owendRightList;
	}

	@Override
	public List<Permission> queryAllCheckByRole(Integer roleCode)
			throws AuthException {
		List<Permission> allRightList = rightDao.selectAll();
		if (allRightList == null || allRightList.isEmpty()) {
			throw new AuthException("未查询到权限");
		}
		for (Permission right : allRightList) {
			right.setOpen(true);
		}

		List<Permission> roleRightList = rightDao.selectByRoleCode(roleCode);
		if (roleRightList == null || roleRightList.isEmpty()) {
			return allRightList;
		}
		checkRelationRight(allRightList, roleRightList);
		return allRightList;
	}

	private void checkRelationRight(List<Permission> allRightList,
			List<Permission> ownRightList) {
		for (Permission right : allRightList) {
			for (Permission ownRight : ownRightList) {
				if (ownRight.getId().equals(right.getId())) {
					right.setChecked(true);
					break;
				}
			}
		}
	}

	@Override
	public boolean save(Permission right) throws AuthException {
		if (right == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (StringUtils.isBlank(right.getName())) {
			throw new AuthException("name不能为空");
		}
		if (right.getParentId() == null) {
			right.setParentId(0);
		}
		int result = rightDao.insert(right);
		if (result < 0) {
			throw new AuthException("操作失败");
		}
		return true;
	}

	@Override
	public boolean modify(Permission right) throws AuthException {
		Permission rightInfo = rightDao.select(right.getId());
		if (rightInfo == null) {
			throw new AuthException("没有对应的权限");
		}
		BeanUtils.copyObject(rightInfo, right);
		int result = rightDao.update(rightInfo);
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

		RolePermission roleRelationRight = new RolePermission();
		roleRelationRight.setRightId(code);
		roleDao.delete(roleRelationRight);

		Permission right = new Permission();
		right.setId(code);
		int result = 0;
		// 删除权限本身
		result = rightDao.delete(right);
		if (result < 0) {
			throw new AuthException("执行失败");
		}

		// 删除权限关联的角色
		RolePermission role = new RolePermission();
		role.setRightId(code);
		roleDao.delete(role);

		return true;
	}

	@Override
	public Permission query(Integer code) throws AuthException {
		Permission right = new Permission();
		right.setId(code);
		List<Permission> list = rightDao.find(right);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Permission> queryChild(Integer parentCode) throws AuthException {
		List<Permission> list = new ArrayList<Permission>();
		if (parentCode == null) {
			Permission fist = new Permission();
			fist.setId(0);
			fist.setParentId(0);
			fist.setName("权限管理树");
			fist.setTypes(0);
			fist.setIsParent(true);
			list.add(fist);
		} else {
			list = rightDao.findChild(parentCode);
			if (list != null && !list.isEmpty()) {
				for (Permission right : list) {
					if (StringUtils.isNotBlank(right.getTmpChildName())) {
						right.setIsParent(true);
					}
				}
			}
		}

		return list;
	}
}
