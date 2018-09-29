package com.byk.rong.common.util;
import com.byk.rong.common.config.BaseConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author:      ykbian
 *@date_time:   2018/9/29 13:33
 *@Description:  创建树形结构
 *@param:
*/
public class BuildTree {


	public static <T> Tree<T> build(List<Tree<T>> nodes) {
		if (nodes == null) {
			return null;
		}

		// 树形结构列表
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
		// 遍历参数中的列表类型的树
		for (Tree<T> children : nodes) {
			// 如果树形结构的父id为“0”，将这个树增加到创建的树中
			String pid = children.getParentId();
			if (pid == null || BaseConstant.TOP_MENU_PARIENT_ID.equals(pid)) {
				topNodes.add(children);
				continue;
			}
			// 一个树里面可以有多个分支，因此可以循环
			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				// 如果
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}
		}
		Tree<T> root = new Tree<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setHasParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}
		return root;
	}
	public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
		for (Tree<T> children : nodes) {
			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);
				continue;
			}
			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}
		}
		return topNodes;
	}
}