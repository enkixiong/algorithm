package week_06.s322;

import java.util.Arrays;

class MemoFlick3Solution {

    private int ans = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        //1.dfs
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int[] coins, int index, int amount, int count) {
        if (index < 0) return;

        int reminder = amount % coins[index];
        int max = amount / coins[index];
        int nextCount = max+count;

        // 检测当前层是否可以
        if (reminder == 0) {
            ans = Math.min(ans, nextCount);
            return;
        }
        for (int current = max; current >= 0; current--) {
            // 提前剪枝
            if (nextCount + 1 >= ans) {
                return;
            }
            dfs(coins, index - 1, reminder, nextCount);

            reminder += coins[index];
            nextCount--;
        }
    }

}
