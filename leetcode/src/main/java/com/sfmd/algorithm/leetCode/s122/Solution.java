package com.sfmd.algorithm.leetCode.s122;

class Solution {

    public int maxProfit(int[] prices) {
        return dfs(prices, 0, false);
    }

    private int dfs(int[] prices, int start, boolean buyed) {
        if (start == prices.length) {
            return 0;
        }
        // 不买也不卖
        int max = dfs(prices, start + 1, buyed);
        // 买/不买
        int transfer = dfs(prices, start + 1, !buyed) + (buyed ? prices[start] : -prices[start]);
        return Math.max(transfer, max);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{7,1,5,3,6,4}));
    }

}
