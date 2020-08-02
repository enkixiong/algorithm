package com.sfmd.algorithm.leetCode.s174;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

class Solution {

    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {

        if (dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }
        memo = new int[dungeon.length][dungeon[0].length];
        memo[dungeon.length - 1][dungeon[0].length - 1] = Math.max(1, 1 - dungeon[dungeon.length - 1][dungeon[0].length - 1]);
        return calMinHP(dungeon, 0, 0);
//        System.out.println(JSON.toJSONString(memo));
//        return result;
    }

    private int calMinHP(int[][] dungeon, int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int minHP = Integer.MAX_VALUE;
        if (x < dungeon.length - 1) {
            minHP = Math.min(minHP, calMinHP(dungeon, x + 1, y));
        }
        if (y < dungeon[x].length - 1) {
            minHP = Math.min(minHP, calMinHP(dungeon, x, y + 1));
        }
        minHP = Math.max(1, minHP - dungeon[x][y]);
        memo[x][y] = minHP;
        return minHP;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
        System.out.println(new Solution().calculateMinimumHP(new int[][]{{1, -4, 5, -99}, {2, -2, -2, -1}}));
    }
}
