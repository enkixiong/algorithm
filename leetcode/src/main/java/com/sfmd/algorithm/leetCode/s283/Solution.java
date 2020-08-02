package com.sfmd.algorithm.leetCode.s283;

class Solution {

    public void moveZeroes(int[] nums) {

        // 借鉴于快排的思想;

        int j = 0; // j 始终指向 当前非零元素需要放的地方
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                j++;
            }
        }

    }



}
