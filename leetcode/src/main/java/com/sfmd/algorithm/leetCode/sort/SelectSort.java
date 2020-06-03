package com.sfmd.algorithm.leetCode.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectSort {

    /**
     * link:https://leetcode-cn.com/problems/minimum-subsequence-in-non-increasing-order/
     */
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int finalResult = 0;
        boolean isFit = true;
        while (isFit){
            int maxOffset = 0;
            int total = 0;
            for (int i =0; i < nums.length; i++){
                total += nums[i];
                if (nums[i] > nums[maxOffset]){
                    maxOffset = i;
                }
            }
            int currentMax = nums[maxOffset];
            nums[maxOffset] = 0;
            result.add(currentMax);
            finalResult += currentMax;
            total -= currentMax;
            isFit = total >= finalResult ;
        }
        return result;
    }

    public List<Integer> minSubsequence2(int[] nums) {
        // 数据
        List<Integer> result = new ArrayList<>();
        // 大小
        int finalResult = 0;

        int total = 0;
        for (int i =0; i < nums.length; i++){
            total += nums[i];
        }

        int spe = total/2;

        int right = nums.length-1;
        while (finalResult <= spe){
            int maxOffset = 0;
            for (int i =0; i <= right; i++){
                if (nums[i] > nums[maxOffset]){
                    maxOffset = i;
                }
            }
            int currentMax = nums[maxOffset];
            nums[maxOffset] = nums[right];
            result.add(currentMax);
            finalResult += currentMax;
            right--;
        }
        return result;
    }

    public List<Integer> minSubsequence3(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            resultList.add(num);
        }
        sum = sum/2;
        Collections.sort(resultList);
        int result = 0;
        for (int i = nums.length-1; i>=0; i--){
            result += resultList.get(i);
            if (result > sum){
                resultList = resultList.subList(i, nums.length);
                Collections.reverse(resultList);
                return resultList;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new SelectSort().minSubsequence3(new int[]{4,3,10,9,8})));
        System.out.println(JSON.toJSONString(new SelectSort().minSubsequence3(new int[]{4,4,7,6,7})));

    }


}
