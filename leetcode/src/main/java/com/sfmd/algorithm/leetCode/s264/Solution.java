package com.sfmd.algorithm.leetCode.s264;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int[] ptr = new int[]{0,0,0};
        int[] seed = new int[]{2,3,5};
        int[] values = new int[]{2,3,5};

        for(int i = 1; i < n; i++){
            int min = Integer.MAX_VALUE;
            for (int value : values) {
                min = Math.min(min, value);
            }
            dp[i] = min;
            for(int j = 0; j < values.length; j++){
                if (values[j] == min){
                    ptr[j]++;
                    values[j] = dp[ptr[j]] * seed[j];
                }
            }
        }
        //System.out.println(JSON.toJSONString(dp));
        return dp[n-1];
    }


    public static void main(String[] args) {
        for(int i : Arrays.asList(10,11)) {
            System.out.println(new Solution().nthUglyNumber(i));
        }
    }
}
