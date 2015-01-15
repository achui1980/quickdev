package com.achui.quick.domain.custom;

import java.util.List;

public abstract class TreeNode {

	private String name;
	private String icon;
	private String href;
	private String target;
	private boolean isLeaf;
	private List<? extends TreeNode> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<? extends TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<? extends TreeNode> children) {
		this.children = children;
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public abstract String toJson();
}
