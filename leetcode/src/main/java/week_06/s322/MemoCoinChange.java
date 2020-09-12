package week_06.s322;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MemoCoinChange {
    public int coinChange(int[] coins, int amount) {
        // 自底向上 例如: 1枚硬币: 1,2,5 2->2,3,6 ... 产生的数据量是: O(coins.size**(amount/coins))
        // 自顶向下, 记忆化递归 最复杂的就是: O(amount)

        // 本题取记忆化递归
        // 如果值不能由coins兑换出来,则在Map中标记为-1

        Arrays.sort(coins);
        Map<Integer,Integer> memo = new HashMap<>();
        for(int coin : coins){
            memo.put(coin,1);
        }

        return dfs(coins, amount, memo);

    }

    private int dfs(int[] coins, int amount, Map<Integer,Integer> memo){
        if(amount <= 0){
            return amount == 0 ? 0 : -1;
        }

        if(memo.containsKey(amount)){
            return memo.get(amount);
        }

        int count = Integer.MAX_VALUE;
        for(int coin : coins){
            int nextCount = dfs(coins,amount-coin,memo);
            if(nextCount != -1){
                count = Math.min(nextCount+1, count);
            }
        }
        count =  count == Integer.MAX_VALUE ? -1 : count;
        memo.put(amount,count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new MemoCoinChange().coinChange(new int[]{1,2,5},100));
    }
}
