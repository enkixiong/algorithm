package com.sfmd.algorithm.leetCode.s343;

class Solution {

    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        return integerBreak(n,dp);
    }

    private int integerBreak(int n, int[] dp){
        if(dp[n] != 0){
            return dp[n];
        }
        int half = n /2;
        int maxValue = 0;
        for(int i = 1; i <= half; i++){
            int currentResult = integerBreak(i,dp)*integerBreak(n-i,dp);

//            dp[n] = Math.max(, dp[n]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().integerBreak(4));
    }

}
