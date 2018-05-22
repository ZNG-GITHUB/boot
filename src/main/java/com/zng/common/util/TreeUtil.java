package com.zng.common.util;

import com.zng.common.entity.TreeNode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

   public static List<TreeNode> buildTree(List<TreeNode> nodes){
       List<TreeNode> tree = new ArrayList<>();
       if(!CollectionUtils.isEmpty(nodes)){
           tree = buildTree(null,nodes);
       }
       return tree;
   }

    private static List<TreeNode> buildTree(Long value, List<TreeNode> nodes) {

        List<TreeNode> tree = new ArrayList<>();
        for(TreeNode node : nodes){
            if((value == null && node.getParentValue() == null) || (node.getParentValue() != null && node.getParentValue().equals(value))){
                List<TreeNode> chilrenTree = buildTree(node.getValue(), nodes);
                if(!CollectionUtils.isEmpty(chilrenTree)){
                    node.setChildren(chilrenTree);
                }
                tree.add(node);
            }
        }
        return tree;
    }

}
