package com.sfmd.algorithm.leetCode.s96;

public class Solution {

    public int numTrees(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] memo = new int[n+1];
        memo[0] = 1;
        memo[1] = 1;
        recursive(n,memo);
        return memo[n];
    }

    private int recursive(int n, int[] memo){
        if(memo[n] != 0){
            return memo[n];
        }
        int result = 0;
        // 以i为
        for (int i = 1; i <= n; i++) {
            result += recursive(i - 1,memo) * recursive(n - i,memo);
        }

        memo[n] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numTrees(18));
    }

}
