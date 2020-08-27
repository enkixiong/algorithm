package week_04.s200;

import com.alibaba.fastjson.JSON;

public class Solution {

    private static int[][] directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                    System.out.println(JSON.toJSONString(grid));
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j){
        if (i <0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = 'X';
        for(int[] direction : directions){
            dfs(grid, i + direction[0], j+direction[1]);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numIslands(new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}}));
    }

}
