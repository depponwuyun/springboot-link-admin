package com.springboot.core.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.springboot.bcode.domain.auth.Permission;

public class PermissionRecursion {

	// 递归将数据tree存储结构
	public static List<Permission> recursion(List<Permission> allList) {
		if (allList == null || allList.isEmpty()) {
			return null;
		}
		Set<Permission> ownedSet = new HashSet<Permission>();
		for (Permission right : allList) {
			ownedSet.add(right);
		}
		allList = new ArrayList<Permission>(ownedSet);

		List<Permission> ownedRightTree = new ArrayList<Permission>();
		for (Permission right : allList) {
			if (right.isRoot()) {
				// 遍历根节点
				buildChild(right, allList);
				ownedRightTree.add(right);
			}
		}
		Collections.sort(ownedRightTree);
		return ownedRightTree;
	}

	/**
	 * 构建下级菜单
	 * 
	 * @param node
	 */
	private static void buildChild(Permission node, List<Permission> allList) {
		List<Permission> childrens = null;
		Integer code = node.getId();
		for (Permission child : allList) {
			// 获取子节点
			if (code.equals(child.getParentId())) {
				if (null == childrens) {
					childrens = new ArrayList<Permission>();
				}
				if (!child.isRoot()) {
					childrens.add(child);
				}
			}
		}
		if (null != childrens && !childrens.isEmpty()) {
			Collections.sort(childrens);
			node.setChildrens(childrens);
			for (Permission child : childrens) {
				buildChild(child, allList);
			}
		}
	}

}
