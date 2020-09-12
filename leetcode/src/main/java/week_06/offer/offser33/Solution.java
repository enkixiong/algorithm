package week_06.offer.offser33;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    int right;

    public boolean verifyPostorder(int[] postorder) {

        // 1.前序+中序 可以构造出二叉树
        // 2.后续+中序 可以构造出二叉树
        // 二叉搜索树+前序/后续 可以构造出二叉树,
        // 因为,二叉搜索的有序性,可以在前序/后序的遍历结果中很快的定位出根节点的位置

        if (postorder.length < 3) {
            return true;
        }
        right = postorder.length - 1;
        dfs(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return right == -1;
    }

    private void dfs(int[] postOrder, int min, int max) {

        if (right == -1) {
            return;
        }
        // 此时这里是一颗平衡二叉树
        // 找到根节点:
        int rootVal = postOrder[right];
        // 检测value的值,是否在范围值内
        if (rootVal <= min || rootVal >= max) {
            return;
        }

        right--;
        // 遍历右子树
        dfs(postOrder, rootVal, max);
        // 遍历左子树
        dfs(postOrder, min, rootVal);
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++){
            if(step(i) != step2(i)){
                System.out.println(i);
            }
        }

        System.out.println(step(6));
        System.out.println(step2(6));

        PriorityQueue<Integer> minHead = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });


    }

    private static int step(int n){
        if(n <= 2){
            return n;
        }
        // dp[i][0]表示当前一步上来的; dp[i][1]表示当前结果是走了两步上来的
        int[][] dp = new int[n+1][2];
        dp[1][0] = 1;
        dp[1][1] = 0;
        dp[2][0] = 1;
        dp[2][1] = 1;
        for(int i = 3; i <= n; i++){
            // 上1阶
            dp[i][0] = dp[i-1][1] + dp[i-1][0];
            // 上两阶
            dp[i][1] = dp[i-2][0];// 上两阶的步数
        }
        return dp[n][0] + dp[n][1];
    }

    private static int step2(int n){
        if(n <= 3){
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4; i <= n; i++){
            dp[i] = dp[i-1]+dp[i-3];
        }
        return dp[n];
    }





}
