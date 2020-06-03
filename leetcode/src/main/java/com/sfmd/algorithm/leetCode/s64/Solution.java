package com.sfmd.algorithm.leetCode.s64;

public class Solution {

    public int sumNums(int n) {

        boolean x =  n > 0 && ( (n+= sumNums(n-1)) > 0);

        return n;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sumNums(3));
    }

}
