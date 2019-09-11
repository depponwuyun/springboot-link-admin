package com.springboot.core.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.springboot.bcode.domain.auth.Permission;

public class RightRecursion {

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

	/***
	 * 构建子节点
	 * 
	 * @param node
	 * @return
	 */
	/*
	 * private static List<Right> buildChildren(Right node, List<Right> allList)
	 * { List<Right> childrens = null; String code = node.getId(); for (Right
	 * child : allList) { // 获取子节点 if (code.equals(child.getParentId())) { if
	 * (null == childrens) { childrens = new ArrayList<Right>(); } if
	 * (!child.isRoot()) { childrens.add(child); } } } return childrens; }
	 */

	/**
	 * 根据obj反向递归查找
	 * 
	 * @param allList
	 * @param obj
	 * @return
	 */
	public static Permission unRecursion(List<Permission> allList, Permission obj) {
		Permission tmp = null;
		// final修饰，引用不可变，只能进行赋值判断
		for (Permission right : allList) {
			if (right.getId().equals(obj.getParentId())) {
				tmp = obj;
				obj = right;
				obj.setBarChild(tmp);
				if (obj.isRoot()) {
					break;
				}
				unRecursion(allList, obj);
			}
		}
		return obj;
	}

	/**
	 * 根据obj反向递归查找
	 * 
	 * @param allList
	 * @param obj
	 * @return
	 */
	public static Permission unTreeRecursion(List<Permission> treeAllList, Permission obj) {
		if (treeAllList == null || treeAllList.isEmpty() || obj == null) {
			return null;
		}
		Permission bar = null;
		// final修饰，引用不可变，只能进行赋值判断
		for (Permission right : treeAllList) {
			bar = new Permission();
			bar = right;
			if (right.getId().equals(obj.getId())) {
				break;
			}
			List<Permission> childrens = right.getChildrens();
			if (childrens != null && !childrens.isEmpty()) {
				// final修饰，引用不可变，只能进行赋值判断
				if (findChild(childrens, obj, bar)) {
					break;
				}
			}
		}

		return bar;
	}

	private static Boolean findChild(List<Permission> childrens, Permission obj, Permission bar) {
		Boolean findBar = false;
		for (Permission child : childrens) {
			if (findBar) {
				break;
			}
			bar.setBarChild(child);
			if (child.getId().equals(obj.getId())) {
				findBar = true;
				break;
			}
			if (child.getChildrens() != null && !child.getChildrens().isEmpty()) {
				findBar = findChild(child.getChildrens(), obj,
						bar.getBarChild());
			}

		}
		return findBar;

	}
}
