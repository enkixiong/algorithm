package com.sfmd.algorithm.leetCode.s741;

import com.alibaba.fastjson.JSON;

public class Solution {

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n+1][n+1];
        findMax(grid, n, dp);

//        System.out.println(JSON.toJSONString(dp));
        int firstResult =  dp[0][0];
        int endX = 0;
        int endY = 0;
        while(firstResult != 0){
            if (grid[endX][endY] == -1){
                return 0;
            }
            firstResult -= grid[endX][endY];
            grid[endX][endY] = 0;
            if (endX != n-1 && dp[endX+1][endY] == firstResult){
                endX++;
            }else{
                endY++;
            }
        }
        if (endX+endY != ((n << 1)-1)){
            return 0;
        }
        firstResult = dp[0][0];
        findMax(grid, n, dp);
        return firstResult+dp[0][0];
    }

    private void findMax(int[][] grid, int n, int[][] dp) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().cherryPickup(new int[][]{{0,1,-1},{1,0,-1},{1,1,1}}));
        System.out.println(new Solution().cherryPickup(new int[][]{{1,1,-1},{1,-1,1},{-1,1,1}}));
        System.out.println(new Solution().cherryPickup(new int[][]{
                {1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},
                {1,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},
                {0,0,0,1,1,1,1}
        }));

    }

//    [[0,1,-1],[1,0,-1],[1,1,1]]

}
