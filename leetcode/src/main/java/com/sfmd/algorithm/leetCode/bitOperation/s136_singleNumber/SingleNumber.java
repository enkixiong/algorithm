package com.sfmd.algorithm.leetCode.bitOperation.s136_singleNumber;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int single = nums[0];
        for (int i = 1; i < nums.length; i++){
            single ^= nums[i];
        }
        return single;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{1,2,1}));
        System.out.println(new SingleNumber().singleNumber(new int[]{1,2,1,2}));
    }

}
