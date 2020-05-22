package com.sfmd.algorithm.leetCode.tree.solution105;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0){
            return null;
        }
        return buildTree(preorder, 0, preorder.length, inorder, 0,inorder.length);
    }


    public TreeNode buildTree(int[] preOrder, int preLeft, int preRight, int[] inOrder, int inLeft, int inRight){
        int center = preOrder[preLeft];
        TreeNode treeNode = new TreeNode();
        treeNode.val = center;
        int inCenterLocation = -1;
        for (int i = inLeft; i< inRight; i++){
            if (inOrder[i] == center){
                inCenterLocation = i;
            }
        }

        int newPreCenter = preLeft+inCenterLocation-inLeft+1;
        if (newPreCenter - (preLeft + 1) > 0) {
            treeNode.left = buildTree(preOrder, preLeft + 1, newPreCenter, inOrder, inLeft, inCenterLocation);
        }
        if (preRight - (newPreCenter) > 0){
            treeNode.right = buildTree(preOrder, newPreCenter, preRight, inOrder, inCenterLocation+1, inRight);
        }
        return treeNode;
    }


    public static void main(String[] args) {
        int[] pre = new int[] {3,9,20,15,7};
        int[] in = new int[]{9,3,15,20,7};

        System.out.println(new Solution().buildTree(pre,in));

        System.out.println(new Solution().buildTree(new int[]{}, new int[]{}));

        System.out.println(new Solution().buildTree(new int[]{1}, new int[]{1}));

        System.out.println(new Solution().buildTree(new int[]{1,2}, new int[]{1,2}));

        System.out.println(new Solution().buildTree(new int[]{1,2}, new int[]{2,1}));


    }

}