package com.sfmd.algorithm.leetCode.s34;

import com.alibaba.fastjson.JSON;

class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = binarySearchLeft(nums, target);
        if (result[0] == -1){
            result[1] = -1;
            return result;
        }
        result[1] = binarySearchRight(nums,result[0],nums.length-1, target);
        return result;
    }

    public int binarySearchLeft(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < a.length && a[low]==value) return low;
        else return -1;
    }

    public int binarySearchRight(int[] a, int low, int high,int value) {
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (high < a.length && a[high]==value) return high;
        else return -1;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().searchRange(new int[]{1,2,3,4},2)));
        System.out.println(JSON.toJSONString(new Solution().searchRange(new int[]{1,2,2,2,2,3,4},2)));
    }

}
