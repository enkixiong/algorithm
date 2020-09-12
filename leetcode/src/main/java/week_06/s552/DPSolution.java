package week_06.s552;

class DPSolution {

    private static final int BASE = 1000000000+7;

    public int checkRecord(int n) {
        // 这里实际上是一个排列组合的问题
        // 剔除掉非法的情况: 1. A的数量 > 1; 2. L的连续数量大于3

        // 两种思路:
        // 1) 正向推导, 选取N个节点,并且根据每个节点进行推导,推导时,剔除非法的情况
        // 2) 逆向推导, 减去所有的非法情况

        // 正向推导:
        // 对于[1,n] 中,没一个元素都有三个选择: [A,L,P] 对进入当前层的状态进行标记:
        // 当前层: 有两种情况:
        // 1) 无缺勤 1.1) 无连续迟到记录;  1.2) 1次连续迟到记录; 1.3) 两次连续迟到记录
        // 2) 有缺勤 2.1) 无连续迟到记录;  2.2) 1次连续迟到记录; 2.3) 两次连续迟到记录

        // 状态转移方程:
        // 选择P的情况
        // dp[i][0][0] = dp[i-1][0][0]+dp[i-1][0][1]+dp[i-1][0][2]  // row1
        // dp[i][1][0] = dp[i-1][1][0]+dp[i-1][1][1]+dp[i-1][1][2]  // row2
        // 选择L的情况
        // dp[i][0][1] = dp[i-1][0][0]
        // dp[i][1][1] = dp[i-1][1][0]
        // dp[i][0][2] = dp[i-1][0][1]
        // dp[i][1][2] = dp[i-1][1][1]
        // 选择A的情况
        // dp[i][1][0] += dp[i-1][0][0] + dp[i-1][1] + dp[i-1][2]; 因为A会中断L的连续

        // 综合所有情况,状态转移方程:
        // dp[i][0][0] = sum(row1)
        // dp[i][0][1] = dp[i-1][0][0]
        // dp[i][0][2] = dp[i-1][0][1]
        // dp[i][1][0] = sum(row1 + row2)
        // dp[i][1][1] = dp[i-1][1][0]
        // dp[i][1][2] = dp[i-1][0][1]

        // 初始值: dp[0][0][0] = 1; dp[0][0][1] = 1; dp[0][1][0] = 1;

        // 考虑状态压缩? 基本只使用了上一次的数据,所以，是可以状态压缩的;

        if(n == 0){
            return 0;
        }

        int[][] dp = new int[2][3];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[1][0] = 1;

        for(int i = 1; i < n; i++){
            int[][] newDP = new int[2][3];
            newDP[0][0] = calResult(dp,1);
            newDP[0][1] = dp[0][0];
            newDP[0][2] = dp[0][1];
            newDP[1][0] = calResult(dp,2);
            newDP[1][1] = dp[1][0];
            newDP[1][2] = dp[1][1];
            dp = newDP;
        }

        return calResult(dp,2);
    }

    private int calResult(int[][] dp, int row){

        int result = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < 3; j++){
                result = (result + dp[i][j])%BASE;
            }
        }
        return result;
    }

}
