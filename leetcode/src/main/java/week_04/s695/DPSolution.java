package week_04.s695;

public class DPSolution {

    public int maxAreaOfIsland(int[][] grid) {

        // 状态转移方程
        // dp[i][j] = dp[i-1][j] + dp[i][j-1] + 1

        // 初始值: 第一行第一列作为起始值,这样不用做特殊的判断,可以减少部分执行时间

        // 特殊条件
        if (grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int rowMax = grid.length;
        int columnMax = grid[0].length;

        int[][] dp = new int[rowMax][columnMax];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < columnMax; j++){
            if (grid[0][j] == 1){
                dp[0][j] = dp[0][j-1] + 1;
            }
        }
        for (int i = 1; i < rowMax; i++){
            if (grid[i][0] == 1){
                dp[i][0] = dp[i-1][0] + 1;
            }
        }
        for(int i = 1; i < rowMax; i++){
            for (int j = 1; j < columnMax; j++){
                if(grid[i][j] == 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < rowMax; i++){
            for (int j = 0; j < columnMax; j++){
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;

    }

}
