package week_04.s123;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class Solution {

    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2]; //[0]持有现金 //[1]持有股票
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
//        System.out.println(JSON.toJSONString(dp));
        return dp[len - 1][0];
    }

    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
//        System.out.println(JSON.toJSONString(dp));
        return dp[len - 1][0];
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // cash：持有现金
        // hold：持有股票
        // 状态数组
        // 状态转移：cash → hold → cash → hold → cash → hold → cash
        int[] cash = new int[len];
        int[] hold = new int[len];

        cash[0] = 0;
        hold[0] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }
        return cash[len - 1];
    }

    public int maxProfit4(int[] prices) {

        int len = prices.length;

        // 以当前节点为终点,所产生的最大利润是多少
        int[] right = new int[len];
        right[0] = 0;
        int min = prices[0];
        for (int i = 1; i< len; i++){
            right[i] = Math.max(prices[i] - min, right[i-1]);
            min = Math.min(min, prices[i]);
        }

        int[] left = new int[len];
        left[len-1] = 0;
        int max = prices[len-1];
        for(int i = len-2; i >= 0; i--){
            left[i] = Math.max(left[i+1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        int result = 0;
        for(int i = 0; i < len-1; i++){
            result = Math.max(right[i] + left[i+1],result);
        }
        return Math.max(result, left[0]);
    }

    public int maxProfit5(int k, int[] prices) {

        if (prices.length == 0 || k == 0){
            return 0;
        }

        int[] dp = new int[k<<1];
        int minHalf = Integer.MIN_VALUE >> 1;
        Arrays.fill(dp, minHalf);
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i < prices.length; i++){
            for(int offset = dp.length-1; offset > 0; offset--){
                if(offset % 2 == 1){
                    dp[offset] = Math.max(dp[offset-1] + prices[i], dp[offset]); // 卖出
                }else{
                    dp[offset] = Math.max(dp[offset-1] - prices[i], dp[offset]); // 买入
                }
            }
            dp[0] = Math.max(-prices[i], dp[0]); // 进价最小
            System.out.println(JSON.toJSONString(dp));
        }
        int result = 0;
        for(int i = 0; i < k; i++){
            result = Math.max(result, dp[(i<<1)+1]);
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().maxProfit2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
//        System.out.println(new Solution().maxProfit2(new int[]{1,2,3,4,5}));
//        System.out.println(new Solution().maxProfit4(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(new Solution().maxProfit5(2,new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }


}
