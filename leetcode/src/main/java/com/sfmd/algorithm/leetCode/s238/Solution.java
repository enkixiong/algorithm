package com.sfmd.algorithm.leetCode.s238;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length-1] = 1;
        for (int i = 1; i < nums.length; i++){
            left[i] = left[i-1]*nums[i-1];
        }
        for (int i = nums.length-2; i >= 0; i--){
            right[i] = right[i+1]*nums[i+1];
        }
        for (int i = 0; i < nums.length; i++){
            left[i] = left[i]*right[i];
        }
        return left;
    }

    public int[] productExceptSelf2(int[] nums){
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++){
            result[i] = result[i-1]*nums[i-1];
        }
        int right = 1;
        for (int i = nums.length-1; i>=0; i--){
            result[i] = result[i]*right;
            right *= nums[i];
        }
        return result;
    }

    public int[] productExceptSelf3(int[] nums){
        int[] output = new int[nums.length];
        int left = 1;
        for (int i = 0; i < nums.length; i++){
            int right = 1;
            for (int j = i+1; j < nums.length; j++){
                right *= nums[j];
            }
            output[i] = left * right;
            left *= nums[i];
        }
        return output;
    }

    public int[] productExceptSelf4(int[] nums){
        int[] output = new int[nums.length];
        Arrays.fill(output,1);
        int left = 1;
        int right = 1;
        int rightPointer = nums.length-1;
        for (int leftPointer = 0; leftPointer < nums.length; leftPointer++){

            output[leftPointer] = left * output[leftPointer];
            left *= nums[leftPointer];

            output[rightPointer] = right * output[rightPointer];
            right *= nums[rightPointer];
            rightPointer--;

        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().productExceptSelf4(new int[]{1,2,3,4})));
    }

}
