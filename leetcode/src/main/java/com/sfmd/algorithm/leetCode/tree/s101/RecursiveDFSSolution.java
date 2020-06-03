package com.sfmd.algorithm.leetCode.tree.s101;

public class RecursiveDFSSolution {

    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode node1, TreeNode node2){
        if (null == node1 && null ==  node2){
            return true;
        }
        if (null == node1 || null == node2){
            return false;
        }
        if (node1.val != node2.val){
            return false;
        }
        return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n11 = new TreeNode(3);
        TreeNode n12 = new TreeNode(4);
        TreeNode n21 = new TreeNode(4);
        TreeNode n22 = new TreeNode(3);

        n0.left = n1;
        n0.right = n2;
        n1.left = n11;
        n1.right = n12;
        n2.left = n21;
        n2.right = n22;

        System.out.println(new RecursiveDFSSolution().isSymmetric(n0));

//        TreeNode n0 = new TreeNode(1);
//        TreeNode n1 = new TreeNode(2);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n12 = new TreeNode(4);
//        TreeNode n22 = new TreeNode(4);
//
//        n0.left = n1;
//        n0.right = n2;
//        n1.right = n12;
//        n2.right = n22;
//
//        System.out.println(new SymmetryBinaryTree().isSymmetric2(n0));

    }



}
