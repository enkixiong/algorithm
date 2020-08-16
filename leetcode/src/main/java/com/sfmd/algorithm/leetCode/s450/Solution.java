package com.sfmd.algorithm.leetCode.s450;

import com.sfmd.algorithm.leetCode.s449.LeetcodeCodec;
import com.sfmd.algorithm.leetCode.tree.TreeNode;

public class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        // 二叉搜索树: 堆得删除元素很想

        // 1. 第一步: 找到待删除的元素

        // 2. 找到待删除元素可以替换该元素的点； 找该节点左子树最右下方的点、右子树最左下方的点

        // 3. 实现替换;

        // 3.1 将替换节点从树中摘除; 实现替换
        TreeNode parent = new TreeNode(-1);
        parent.right = root;
        TreeNode node = root;
        while(node != null && node.val != key){
            parent = node;
            if(node.val < key){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        if ( null == node ){
            return root;
        }

        delNode(parent,node);
        return root.val == key ? parent.right : root;
    }

    // 删除node节点,并返回顶替 已删除节点的节点
    private void delNode(TreeNode parent,TreeNode node){

        // 叶子节点的情况
        if (node.left == null && node.right == null){
            if (parent.left == node){
                parent.left = null;
            }else{
                parent.right = null;
            }
            return;
        }

        // 找到待替换的点: 莫里斯遍历，左节点的最右节点
        TreeNode replacedParent = node;
        TreeNode replaced = node.left;
        while(replaced != null && replaced.right != null){
            replacedParent = replaced;
            replaced = replaced.right;
        }

        // 替换
        if(replaced != node.left) {
            // 从待替换的点中摘除
            replacedParent.right = null;
            replaced.left = node.left;
        }
        replaced.right = node.right;

        if (parent.left == node){
            parent.left = replaced;
        }else{
            parent.right = replaced;
        }

    }

    public static void main(String[] args) {
        LeetcodeCodec codec = new LeetcodeCodec();
        TreeNode root = codec.deserialize("1,null,2");
        root = new Solution().deleteNode(root,1);
        System.out.println(codec.serialize(root));
    }

}
