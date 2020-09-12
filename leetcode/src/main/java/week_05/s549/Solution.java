package week_05.s549;

import com.alibaba.fastjson.JSON;
import com.sfmd.algorithm.leetCode.s449.LeetcodeCodec;
import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.Arrays;

class Solution {

    public int longestConsecutive(TreeNode root) {

        // 1. 左子树的连续路径

        // 2. 右子树最长的连续路径

        // 3. 左子树(包含根节点,单边的)最长的上升连续路径+右子树(包含根节点)最长的连续下降路径

        return dfs(root)[0];
    }

    // int[0] 当前子树的连续序列路径的长度
    // int[1] 包含当前节点的上升路径的长度
    // int[2] 包含当前节点的下降路径的长度
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0, 0};
        }

        if (node.left == null && node.right == null) {
            return new int[]{1, 1, 1};
        }

        int[] result = new int[]{1, 1, 1};
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        if (node.left != null) {
            maintain(node, result, left, node.left);
        }

        if (node.right != null) {
            maintain(node, result, right, node.right);
        }
        result[0] = Math.max(result[0], result[1] + result[2] - 1);
        return result;
    }

    private void maintain(TreeNode node, int[] result, int[] childData, TreeNode child) {
        int abs = Math.abs(child.val - node.val);
        // 说明不连续
        if (abs == 1) {
            if (child.val < node.val) {
                // 左侧的节点比节点小
                result[1] = Math.max(result[1], childData[1] + 1);
            } else {
                result[2] = Math.max(result[2], childData[2] + 1);
            }
        }
        result[0] = Math.max(result[0], childData[0]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new LeetcodeCodec().deserialize("5,4,null,null,2,3,1")));
    }
}
