package com.sfmd.algorithm.leetCode.tree.s101;

import java.util.ArrayList;
import java.util.List;

public class IteratorDFSSolution {

    public boolean isSymmetric(TreeNode root) {

        if (root == null){
            return true;
        }

        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root.left);
        nodeList.add(root.right);
        while(!nodeList.isEmpty()){
            TreeNode left = nodeList.remove(0);
            TreeNode right = nodeList.remove(0);
            if (left == null && right == null){
                continue;
            }
            if (left == null || right == null){
                return false;
            }
            if (left.val != right.val){
                return false;
            }
            nodeList.add(left.left);
            nodeList.add(right.right);
            nodeList.add(left.right);
            nodeList.add(right.left);
        }
        return true;
    }


    public static void main(String[] args) {
//        TreeNode n0 = new TreeNode(1);
//        TreeNode n1 = new TreeNode(2);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n11 = new TreeNode(3);
//        TreeNode n12 = new TreeNode(4);
//        TreeNode n21 = new TreeNode(4);
//        TreeNode n22 = new TreeNode(3);
//
//        n0.left = n1;
//        n0.right = n2;
//        n1.left = n11;
//        n1.right = n12;
//        n2.left = n21;
//        n2.right = n22;
//
//        System.out.println(new DFSSolution().isSymmetric(n0));

        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n12 = new TreeNode(4);
        TreeNode n22 = new TreeNode(4);

        n0.left = n1;
        n0.right = n2;
        n1.right = n12;
        n2.right = n22;

        System.out.println(new IteratorDFSSolution().isSymmetric(n0));
    }



}
