package com.sfmd.algorithm.leetCode.s35;

class Solution {

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid+1;
            }
        }
        return nums[low] < target ? low +1 : low;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().searchInsert(new int[]{1,2,4,5},3));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},7));
        System.out.println(new Solution().searchInsert(new int[]{1,3,5,6},-1));
    }

}
