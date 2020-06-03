package com.sfmd.algorithm.leetCode.s837;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class Solution {

    public double new21Game(int n, int k, int w) {

        if (n < k){
            return 0.0;
        }
        if (n > k+w-1){
            return 1.0;
        }
        double[] dp = new double[k+w];
        for (int i = k; i <= n; i++){
            dp[i] = 1.0;
        }

        double sum = n-k+1;
        for (int i = k-1; i >= 0; i--){
            dp[i] = sum/w;
            sum = sum - dp[i+w] + dp[i];
        }

        return dp[0];
    }

    public double new21Game2(int n, int k, int w) {
        if (n <k){
            return 0.0;
        }
        if (n >= k+w-1){
            return 1.0;
        }

        int maxOffset = k+w; // 最高的台阶数
        double[] stepRatio = new double[maxOffset];
        // 从台阶0开始出发
        stepRatio[0] = 1;
        for (int i = 1; i < k; i++){
            int start = Math.max(0,i-w);
            double sum = 0.0;
            for (int j = start; j < i; j ++){
                sum += stepRatio[j];
            }
            stepRatio[i] += sum/w;
        }
        for (int i = k; i< stepRatio.length; i++){
            int start = Math.max(0,i-w);
            int end = Math.min(i,k);
            for (int j = start; j < end; j ++){
                stepRatio[i] += stepRatio[j]/w;
            }
        }

        double interval = 0.0;
        double sum = 0.0;
        for (int i = k; i <= k+w-1; i++){
            if (i <= n){
                interval += stepRatio[i];
            }
            sum += stepRatio[i];
        }
        return interval/sum;
    }



    public static void main(String[] args) {
//        System.out.println(new Solution().new21Game2(10,1,10));
        System.out.println(new Solution().new21Game2(6,1,10));
        System.out.println(new Solution().new21Game2(21,17,10));
        System.out.println(new Solution().new21Game2(0,0,2) == 1);
        System.out.println(new Solution().new21Game2(0,0,1) == 1);


//        System.out.println(new Solution().new21Game(9301,9224,9771));
    }

}
