package com.sfmd.algorithm.leetCode.s190;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

class Solution {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(new Solution().reverseBits(0b00000010100101000001111010011100)));
        System.out.println(Integer.toBinaryString(new Solution().reverseBits(0b11111111111111111111111111111101)));
    }

}
