package week_04.homework.s122_maxProfit;

public class Solution {

    public int maxProfit(int[] prices) {

        // 边界条件
        if(prices.length == 0){
            return 0;
        }

        // 初始值
        int cash = 0;
        int profit = -prices[0];

        // DP
        for(int i = 1; i < prices.length; i++){
            // 状态转移公式
            cash = Math.max(profit + prices[i], cash);
            // 状态转移公式
            profit = Math.max(profit, cash - prices[i]);
        }

        // 返回结果
        return cash;
    }

}
