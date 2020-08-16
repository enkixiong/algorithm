package com.sfmd.algorithm.leetCode.tree;

public class TreeNode {

    private static final Integer NULL = Integer.MIN_VALUE;

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    /**
     *
     * @param nodes：完全二叉树的前序数组
     */
    public static TreeNode buildTreeNode(Integer[] nodes){
        return build(nodes,0);
    }

    private static TreeNode build(Integer[] nodes, int offset){
        if (offset >= nodes.length || nodes[offset] == null || NULL.equals(nodes[offset])) {
            return null;
        }
        TreeNode node = new TreeNode(nodes[offset]);
        node.left = build(nodes, (offset<<1)+1);
        node.right = build(nodes, (offset<<1)+2);
        return node;
    }
}
