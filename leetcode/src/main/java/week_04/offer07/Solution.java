package week_04.offer07;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

public class Solution {

    private int preOffset;

    private int inOffset;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 规律: 一定是先处理完左子树再处理右子树

        // 何时停止序列化左子树呢?
        return buildTree(preorder, inorder, Integer.MAX_VALUE);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int end){

        // terminator
        if(preOffset >= preorder.length){
            return null;
        }

        if(inorder[inOffset] == end){
            inOffset++;
            return null;
        }

        int value = preorder[preOffset++];
        TreeNode node = new TreeNode(value);
        node.left = buildTree(preorder,inorder,value);
        node.right = buildTree(preorder, inorder, end);

        return node;
    }

    public static void main(String[] args) {
        TreeNode node = new Solution().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(Integer.MAX_VALUE / 1000000007);

        System.out.println(Integer.MAX_VALUE / 4 );

        long x = (536870913L * 2 * 2 * 2) % 1000000007;
        long y = ((((536870913L * 2) % 1000000007) * 2 ) % 1000000007) * 2 % 1000000007;

        System.out.println(x + " -> "+ y);
    }

}
