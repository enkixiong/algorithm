package com.sfmd.algorithm.leetCode.s81;

class Solution {

    public boolean search(int[] nums, int target) {
        int offset = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                offset = i;
            }
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int midOffset = (mid + offset) % nums.length;
            if (nums[midOffset] > target) {
                high = mid - 1;
            } else if (nums[midOffset] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{2,5,6,0,0,1,2},0));
        System.out.println(new Solution().search(new int[]{2,5,6,0,0,1,2},7));
    }

}
