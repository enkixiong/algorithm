package week_06.s322;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MemoFlickCoinChange {
    private final Map<Integer,Integer> memo = new HashMap<>();
    private int answer = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length-1, 0,amount);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void dfs(int[] coins, int index, int current, int amount) {
        if(index < 0){
            return;
        }

        int reminder = amount % coins[index];
        if(reminder == 0){
            answer = Math.min(current+amount/coins[index],answer);
        }
        for(int i = amount/coins[index]; i >= 0; i--){
            if(i+current > answer){
                return;
            }
            dfs(coins, index-1,current+i, reminder);
            reminder += coins[index];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MemoFlickCoinChange().coinChange(new int[]{2,3,5},1));
    }

}
