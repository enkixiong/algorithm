package com.sfmd.algorithm.leetCode.s204;

import java.util.Arrays;

class Solution {

    public int countPrimes(int n) {

        if(n <= 2){
            return 0;
        }

        // DP
        boolean[] dp = new boolean[n/2];
        // 边界值
        int sqrt = ((Double)(Math.sqrt(n))).intValue();

        // 选取起始的素数
        int nextIndex = 1;
        int nextPrime = (nextIndex<<1)+1;
        while(nextPrime <= sqrt){
            // System.out.println(nextPrime);
            // 将素数的倍数作为
            // next += next << 1;
            int next = (nextPrime << 1) + nextPrime;
            while(next < n){
                dp[next>>1] = true;
                next += nextPrime << 1;
            }

            // 选取下一个素数
            while(++nextIndex < dp.length && dp[nextIndex]){
            }
            nextPrime = (nextIndex << 1) +1;
        }

        // 扫描结果
        int size = 1;
        for(int i = 1; i < dp.length; i++){
            if(!dp[i]){
                size++;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        for (int i : Arrays.asList(0,1,2,3,10,100,101)){
            System.out.println(new Solution().countPrimes(i));
        }
    }
}
