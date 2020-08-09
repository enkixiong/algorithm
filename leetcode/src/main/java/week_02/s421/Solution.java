package week_02.s421;

class Solution {

    private static final int MAX = 30;

    public int findMaximumXOR(int[] nums) {
        return trieSolution(nums);
    }

    private int forceSolution(int[] nums){
        // 暴力解决方案
         int max = 0;

         for ( int i = 0; i < nums.length; i++){
             for ( int j = i; j < nums.length; j++){
                 max = Math.max(nums[i] ^ nums[j], max);
             }
         }

         return max;
    }

    private int trieSolution(int[] nums){
        TreeNode root = new TreeNode();
        for(int num : nums){
            addToTrieTree(root, num);
        }
        return dfs(root, root, MAX);
    }

    private int dfs(TreeNode left, TreeNode right, int level){
        if(level == -1 || left == null || right == null){
            return 0;
        }
        int max = 0;
        if (left.left != null && right.right != null){
            max = (1 << level) + dfs(left.left, right.right, level -1);
        }
        if (left.right != null && right.left != null){
            max = Math.max((1 << level) + dfs(left.right, right.left, level -1), max);
        }

        if (max != 0){
            return max;
        }

        if (left.left != null && right.left != null){
            max = dfs(left.left, right.left, level -1);
        }

        return Math.max(max, dfs(left.right, right.right, level -1));

    }

    private void addToTrieTree(TreeNode root, int num){
        TreeNode node = root;
        int flag = 0x0001 << MAX;
        for(int i = 0; i <= MAX; i++){
            // 检测是否含有该位
            boolean leftExist = (flag & num)  == 0;
            if(leftExist){
                if(node.left == null){
                    node.left = new TreeNode();
                }
                node = node.left;
            }else{
                if(node.right == null){
                    node.right = new TreeNode();
                }
                node = node.right;
            }
            flag = flag >> 1;
        }
    }

    private static class TreeNode {

        private TreeNode left; // 0
        private TreeNode right; // 1

    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMaximumXOR(new int[]{0,1}));
        System.out.println(new Solution().findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(new Solution().findMaximumXOR(new int[]{3,10,5,3,2,8}));
    }
}
