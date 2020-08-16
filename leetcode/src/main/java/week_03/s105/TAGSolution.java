package week_03.s105;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

class TAGSolution {
    private int pre = 0, in = 0;
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return recursive(preOrder, inOrder, Integer.MAX_VALUE);
    }

    private TreeNode recursive(int[] preOrder, int[] inOrder, int stop) {
        if (pre >= preOrder.length) {
            return null;
        }
        if (inOrder[in] == stop) {
            in++;
            return null;
        }
        int curVal = preOrder[pre++];
        TreeNode cur = new TreeNode(curVal);
        cur.left = recursive(preOrder, inOrder, curVal);
        cur.right = recursive(preOrder, inOrder, stop);
        return cur;
    }
}
