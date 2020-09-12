package week_06.s1000;

import java.util.Arrays;

class DPSolution {

    private int step = 0;
    int[] info;

    public int mergeStones(int[] stones, int k) {
        // 1. 检测是否可以合并; 取模,并且余数于除值相加,最后看结果是否能为1
        // 如果通过,该怎么办?

        step = k-1;
        if(!canMerge(stones.length)){
            return -1;
        }

        if(stones.length == 1){
            return 0; // 注意这里是合并的成本
        }

        // 开始合并操作,对第一个例子进行分析
        // 目标: 将所有的元素 By K 大小为维度,进行合并
        // [3,2,4,1] --> [5,5] --> [10]
        // 不同的合并方式,会产生不同的结果的原因是: 合并时,会导致区间内的元素会被重新计算一次;

        // dp[i][j]表示当前区间合并为1堆得最终成本;
        // dp[i][j] = dp[i][X1] + dp[X1][X2] + .... + dp[XK-1][j];
        // 也就是在[i,j]中,插入K-1根棒子,将[i,j]区间划分为K个连续的子区间;
        // 那么如何枚举K-1根棒子?
        //    全部枚举, 用DP记录信息
        //    有没有更好的办法?

        info = new int[stones.length+1];
        for(int i = 1; i <= stones.length; i++){
            info[i] = stones[i-1] + info[i-1];
        }
        //System.out.println(JSON.toJSONString(info));

        // 开始计算
        int[][] dp = new int[stones.length][stones.length];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int i = 0; i < dp.length; i++){
            dp[i][i] = 0;
        }

        for(int duration = k; duration <= stones.length; duration += step){
            int end = stones.length - duration;
            for(int i = 0; i <= end; i++){
                int j = i+duration-1; // 闭区间[i,j]的长度为duration, j = i + duration - 1
                cal(dp,i,j,k);
                dp[i][j] += info[j+1] - info[i];
            }
        }

        return dp[0][stones.length-1];

    }

    private boolean canMerge(int len){
        return (len-1) % step == 0;
    }

    // 将区间[i,j] 分为K堆,  切割出的区间,必须要允许canMerge, 那是否可以预先计算出,是否可以canMerge的区间长度?
    // 不用,满足的规律是: j-i+1%(k-1) == 0
    // 请注意,K这里只是表示区间的数量,并不代表合并的次数,因为当前区间表示的意思,都是K个连续区间一次性合并产生的值
    private int cal(int[][]dp, int i, int j, int k){
        if(k == 1){
            // 表示不需要再进行合并,区间长度为分割数组的长度,简单的将值相加就可以了
            return dp[i][j];
        }
        int end = j - k+1;
        // 分割为连续的K堆石头
        for(int offset = i; offset <= end; offset+= step){
            int candidate = (dp[i][offset]) + cal(dp,offset+1, j, k-1);
            dp[i][j] = Math.min(dp[i][j], candidate);
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new DPSolution().mergeStones(new int[]{3,2,4,1},2));
        System.out.println(new DPSolution().mergeStones(new int[]{3,2,4,1},3));
        System.out.println(new DPSolution().mergeStones(new int[]{3,5,1,2,6},3));
    }
}
