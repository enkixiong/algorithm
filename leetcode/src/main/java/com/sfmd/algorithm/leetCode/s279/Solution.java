package com.sfmd.algorithm.leetCode.s279;

import java.util.Arrays;

public class Solution {

    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    public int numSquares(int n) {
        if(n == 0){
            return 0;
        }
        // four-square and three-square theorems.
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;

        if (this.isSquare(n))
            return 1;
        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i) {
            if (this.isSquare(n - i * i))
                return 2;
        }
        // bottom case of three-square theorem.
        return 3;
    }

    public static void main(String[] args) {
        for (int i : Arrays.asList(0,1,2,3,4,5,6,30)){
            System.out.println(new Solution().numSquares(i));
        }
    }

}
