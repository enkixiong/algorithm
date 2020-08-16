package week_03.s236;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 退出条件 & 处理当前层的逻辑
        if(root == null || root == p || root == q){
            return root;
        }

        // drill down
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p,q);

        // 汇总当前层的结果 merge
        return left != null && right != null ? root :
                (left == null ? right : left);

        // 清除当前层的状态
    }
}
