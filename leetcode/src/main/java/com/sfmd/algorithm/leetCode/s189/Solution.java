package com.sfmd.algorithm.leetCode.s189;

import com.alibaba.fastjson.JSON;

class Solution {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
//        rotateViolent(nums,k);
        rotateSwap(nums, k);
//        rotateReverse(nums,k);
    }

    /**
     * 暴力解决方案; 每次向右移动一位; 移动K次
     */
    private void rotateViolent(int[] nums, int k) {
        int endIndex = nums.length - 1;
        for (int i = 0; i < k; i++) {
            int tmp = nums[endIndex];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = tmp;
        }
    }

    /**
     * 选取任意一个元素, 放置在转换后放置的位置；<br/>
     * 被放置的位置元素，作为下一个待放置的元素; <br/>
     * 如何证明? 如何证明 以一个固定间隔跳跃式的访问一个数组, 访问元素有且仅有一次的访问数组?
     */
    private void rotateSwap(int[] nums, int k) {
        int start = -1;
        int count = 0;
        while(count < nums.length){
            int index = ++start;
            int value = nums[index];
            while(count < nums.length){
                int nextIndex = (index + k) % nums.length;
                int nextValue = nums[nextIndex];
                nums[nextIndex] = value;
                count++;
                index = nextIndex;
                value = nextValue;
                if (index == start){
                    break;
                }
            }
        }
    }

    private void rotateReverse(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums,0,k-1);
    }

    private void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int tmp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6,7,8,9,10};
        new Solution().rotate(data, 4);
        System.out.println(JSON.toJSONString(data));
    }

}
