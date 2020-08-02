package com.sfmd.algorithm.leetCode.s329;

class Solution {

    private final int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int[][] memo = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(dfs(matrix,memo, Integer.MIN_VALUE,i,j), max);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int[][] memo,int before, int i, int j){
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] <= before){
            return 0;
        }
        if (memo[i][j] != 0){
            return memo[i][j];
        }
        int max = 0;
        for (int[] dire : directions) {
            max = Math.max(max,dfs(matrix,memo, matrix[i][j],i+dire[0], j+dire[1]));
        }
        memo[i][j] = 1 + max;
        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
    }
}
