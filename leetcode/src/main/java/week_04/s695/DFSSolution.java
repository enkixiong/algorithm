package week_04.s695;

import com.alibaba.fastjson.JSON;

public class DFSSolution {

    private static final int INVALID = -1;

    private static final int[][] directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int max = 0;

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    max = Math.max(dfs(grid, i, j),max);
                }
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        if ( i<0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1){
            return 0;
        }

        grid[i][j] = INVALID;
        int result = 1;
        for(int[] direction : directions){
            result += dfs(grid, i + direction[0], j + direction[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DFSSolution().maxAreaOfIsland(new int[][]{{1,1},{1,1}}));
        System.out.println(new DFSSolution().maxAreaOfIsland(new int[][]{{0,1},{1,1}}));
    }
}
