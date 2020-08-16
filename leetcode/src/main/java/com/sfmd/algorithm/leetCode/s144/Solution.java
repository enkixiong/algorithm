package com.sfmd.algorithm.leetCode.s144;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
//            System.out.println(node.val);
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            }
            else {
                // 先建立后继指针
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                // 找到了待建立后继指针的节点
                if (predecessor.right == null) {
                    // 先访问根节点
                    output.add(node.val);
                    // 建立后继指针
                    predecessor.right = node;
                    // 往左走
                    node = node.left;
                }
                else{
                    // 如果当前访问的是后继指针,并且后继指针指向了自己
                    predecessor.right = null;
                    // 走向右侧
                    node = node.right;
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(binarySearchRightBegin(new char[]{'2','1'}, 0 ,1));
    }

    // start 为根节点
    private static int binarySearchRightBegin(char[] array, int start, int end){

        // 特殊判断
        if(start == end || array[end] < array[start]){
            return end+1;
        }
        int left = start+1;
        int right = end;

        while(left < right){
            int mid = (left + right) >> 1;
            if (array[mid] > array[start]){

                if(mid > 0 && array[mid-1] < array[start]){
                    return mid;
                }
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return right;
    }





}
