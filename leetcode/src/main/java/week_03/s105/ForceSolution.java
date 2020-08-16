package week_03.s105;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

public class ForceSolution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
            return null;
        }
        return buildTree(preorder, 0, preorder.length, inorder, 0,inorder.length);
    }


    public TreeNode buildTree(int[] preOrder, int preLeft, int preRight, int[] inOrder, int inLeft, int inRight){
        int center = preOrder[preLeft];
        TreeNode treeNode = new TreeNode(center);
        if (preRight - preLeft == 1){
            return treeNode;
        }
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
}
