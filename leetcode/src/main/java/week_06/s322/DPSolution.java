package week_06.s322;

import java.util.Arrays;

class DPSolution {

    private static final int INVALID = Integer.MAX_VALUE >> 1;

    public int coinChange(int[] coins, int amount) {

        if(coins.length == 0 || amount < coins[0]){
            return amount == 0 ? 0 : -1;
        }

        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        Arrays.fill(dp,INVALID);
        dp[0] = 0;
        for(int coin : coins){
            dp[coin] = 1;
        }

        for(int i = coins[0]+1; i <= amount; i++){
            for (int coin : coins) {
                int preVal = i - coin;
                if (preVal >= 0) {
                    dp[i] = Math.min(dp[preVal] + 1, dp[i]);
                }
            }
        }

        return dp[amount] == INVALID ? -1 : dp[amount];
    }



}
