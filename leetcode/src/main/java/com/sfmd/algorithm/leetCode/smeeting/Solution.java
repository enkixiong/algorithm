package com.sfmd.algorithm.leetCode.smeeting;

class Solution {

    private int findMin(int x, int[] num){

        int min = Integer.MAX_VALUE;
        // 1. 暴力求解
        for (int i = 0; i < num.length-1; i++) {
            for (int j = i+1; j < num.length-1; j++) {
                //找到两个数字的最小公倍数
                int result = (num[i] - num[j])/(i - j);
                min = Math.min(min, result);
            }
        }
        return min;
    }

}
