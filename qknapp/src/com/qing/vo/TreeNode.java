package com.qing.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 构建树形结构的实体
 * 
 * @author huangqingjian/0050
 * @see [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since 深烟智能物流管理平台, 2013-1-10
 */
public class TreeNode {

    // 页面树节点ID
    private String id;

    // 显示文本
    private String text;

    // 子节点
    private List<TreeNode> children = new ArrayList<TreeNode>();

    // 是否展开
    private boolean expanded = false;

    /**
     * @return Returns the id.
     */
    public String getId() {
	return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return Returns the text.
     */
    public String getText() {
	return text;
    }

    /**
     * @param text
     *            The text to set.
     */
    public void setText(String text) {
	this.text = text;
    }

    /**
     * @return Returns the leaf.
     */
    public boolean getLeaf() {
	if ((this.children == null) || (this.children.size() == 0)) {
	    return true;
	}
	return false;
    }

    /**
     * 添加子节点
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @Company: Tai woo. Co., Ltd
     * @param node
     */
    public void addChild(TreeNode node) {
	if (children == null) {
	    children = new ArrayList<TreeNode>();
	}
	children.add(node);
    }

    public boolean equals(TreeNode node) {
	if (node.getId().equals(this.id)) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * @param expanded
     *            The expanded to set.
     */
    public void setExpanded(boolean expanded) {
	this.expanded = expanded;
    }

    /**
     * @return Returns the expanded.
     */
    public boolean isExpanded() {
	return expanded;
    }

    public List<TreeNode> getChildren() {
	return children;
    }

    public void setChildren(List<TreeNode> children) {
	this.children = children;
    }

}
