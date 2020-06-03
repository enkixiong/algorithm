package com.sfmd.algorithm.leetCode.find.s1;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> offsetValueMap = new HashMap<>(nums.length);
        for (int i = 0; i< nums.length; i++){
            int num = nums[i];
            int another = target - num;
            if (!offsetValueMap.containsKey(another)){
                offsetValueMap.put(num, i);
                continue;
            }
            return new int[]{offsetValueMap.get(another),i};
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new TwoSum().twoSum(new int[]{2, 7, 11, 15},9)));
        System.out.println(JSON.toJSONString(new TwoSum().twoSum(new int[]{-1, 5, 11, 5},10)));
        System.out.println(JSON.toJSONString(new TwoSum().twoSum(new int[]{ 5, 11, 5,-1},10)));


    }

}
