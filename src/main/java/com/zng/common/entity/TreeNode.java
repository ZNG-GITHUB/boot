package com.zng.common.entity;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements Serializable {

    private Long value;
    private String label;
    private Long parentValue;
    private List<TreeNode> children;

    public TreeNode(Long value,String label,Long parentValue){
        this.value = value;
        this.label = label;
        this.parentValue = parentValue;
    }

    public TreeNode(Long value,String lable){
        this.value = value;
        this.label = lable;
    }

    public Long getParentValue() {
        return parentValue;
    }

    public void setParentValue(Long parentValue) {
        this.parentValue = parentValue;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

}
