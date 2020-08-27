package week_04.s322;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class CoinChange {

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        return dfs(coins, coins.length - 1, amount);
    }

    private int dfs(int[] coins, int endOffset, int amount) {
        if (endOffset < 0) {
            return -1;
        }
        int maxCount = amount / coins[endOffset];
        int reminder = amount % coins[endOffset];
        // 贪心: 能用更大面额进行一次性兑换,那么,就不需要再用小面额进行尝试
        if (reminder == 0) {
            return maxCount;
        }
        int current = amount + 1;
        for (int j = maxCount; j >= 0; j--) {
            // 用更小的面值进行计算
            int next = dfs(coins, endOffset - 1, reminder);
            // 这里是否启用贪心? 我怎么知道贪心一定有效果?
            if (next != -1) {
                current = Math.min(current, j + next);
            }
            reminder += coins[endOffset];
        }
        current = current > amount ? -1 : current;
        return current;
    }

    public int coinChange2(int[] coins, int amount) {
        return dfs(0, coins, amount);
    }

    private int dfs(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = dfs(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    public int force(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }


        // 初始的种子: coins 1
        // 不断的进行累加: 首先达到目标值的,就是最优解;就像上台阶一样,coins是每次可以跨越的台阶数
        Arrays.sort(coins);
        // 初始化:
        List<Integer> seed = new ArrayList<>(coins.length);// 已经明确是不同的面值,则不需要去重
        for (int coin : coins) {
            // 去掉为0的元素
            if (coin != 0) {
                seed.add(coin);
            }
        }

        // 特殊情况,只是一个币种,则不需要进行重复性判断
        if (seed.size() == 1) {
            return amount % seed.get(0) == 0 ? amount / seed.get(0) : -1;
        }

        int maxStep = amount / coins[0] + 1;
        Set<Integer> preLevel = new HashSet<>();
        preLevel.add(0);
        // seed 已经是第一步,所以,从第二步开始计算
        for (int step = 1; step <= maxStep; step++) {
            // 决策树的这一层
            Set<Integer> level = new HashSet<>();
            for (int coin : seed) {
                for (int preLevelValue : preLevel) {
                    level.add(preLevelValue + coin);
                }
            }
            if (level.contains(amount)) {
                return step;
            }
            preLevel = level;
        }
        return -1;
    }

    private static final Integer INVALID = -1;

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INVALID);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            // 说明不可达到
            if (dp[i] == INVALID) {
                continue;
            }
            for (int coin : coins) {
                int next = i + coin;
                if (next <= amount) {
                    dp[next] = dp[next] == INVALID ? dp[i] + 1 : Math.min(dp[i] + 1, dp[next]);
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(new CoinChange().coinChange(new int[]{1,2,5}, 11));
        System.out.println(new CoinChange().coinChange(new int[]{2}, 3));
        System.out.println(new CoinChange().coinChange(new int[]{1,2}, 2));



        System.out.println(new CoinChange().force(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(new CoinChange().coinChange2(new int[]{186, 419, 83, 408}, 6249));

//        System.out.println(new CoinChange().coinChange(new int[]{486,156,330,192,457,71,99},8175));
//        System.out.println(new CoinChange().coinChange(new int[]{3,7,405,436},8839));
//        System.out.println(new CoinChange().coinChange2(new int[]{3,7,405,436},8839));

    }

}
