package week_06.s322;

import java.util.Arrays;

class MemoFlick2Solution {

    private int ans = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        //1.dfs
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int[] coins, int index, int amount, int count) {
        if (index < 0) return;

        if (amount % coins[index] == 0) {
            ans = Math.min(ans, count + amount / coins[index]);
            return;
        }
        for (int current = amount / coins[index]; current >= 0; current--) {
            int nextAmount = amount - current * coins[index];
            int nextCount = count + current;
            // 提前剪枝
            if (nextCount + 1 >= ans) {
                return;
            }
            dfs(coins, index - 1, nextAmount, nextCount);
        }
    }

}
