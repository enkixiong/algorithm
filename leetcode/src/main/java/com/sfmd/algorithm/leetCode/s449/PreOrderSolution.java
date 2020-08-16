package com.sfmd.algorithm.leetCode.s449;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

public class PreOrderSolution {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preOrder(root, builder);
        return builder.toString();
    }

    private void preOrder(TreeNode node, StringBuilder builder){
        // 退出条件
        if(node == null){
            return;
        }
        builder.append(node.val).append(' ');
        preOrder(node.left, builder);
        preOrder(node.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        String[] splited = data.split(" ");
        int[] array = new int[splited.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(splited[i]);
        }
        return dfs(array, 0, array.length-1);
    }

    private TreeNode dfs(int[] array, int start, int end){
//        System.out.println(String.format("[%d,%d]", start, end));
        // 特殊情况2: 没有元素
        if(start > end){
            return null;
        }
        TreeNode node = new TreeNode(array[start]);
        // 特殊情况1: 只有一个元素
        int rightStart = binarySearchRightBegin(array, start, end);
        // System.out.println(rightStart);
        node.left = dfs(array, start+1, rightStart-1);
        node.right = dfs(array, rightStart, end);

        return node;
    }

    private static int binarySearchRightBegin(int[] array, int start, int end){

        // 特殊判断
        if(array[end] <= array[start]){
            return end+1;
        }
        int left = start+1;
        int right = end;

        while(left < right){
            int mid = (left + right) >> 1;
            if (array[mid] > array[start]){
                if(array[mid-1] <= array[start]){
                    return mid;
                }
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        PreOrderSolution s = new PreOrderSolution();
        System.out.println(s.serialize(TreeNode.buildTreeNode(new Integer[]{
                41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23
        })));
//        Solution s = new Solution();
        System.out.println(s.deserialize(s.serialize(TreeNode.buildTreeNode(new Integer[]{
                2,1
        }))));
        System.out.println(s.deserialize(s.serialize(TreeNode.buildTreeNode(new Integer[]{
                2,1,3
        }))));
    }

}
