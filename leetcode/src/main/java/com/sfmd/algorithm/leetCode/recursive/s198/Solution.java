package com.sfmd.algorithm.leetCode.recursive.s198;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int rob(int[] nums) {
        int[] cache = new int[nums.length + 3];
        Arrays.fill(cache, -1);
        return Math.max(rob(nums, 0, cache), rob(nums, 1, cache));
    }

    public int rob(int[] nums, int start, int[] cache) {
        if (cache[start] != -1) {
            return cache[start];
        }
        if (start == nums.length - 1 || start == nums.length - 2) {
            return nums[start];
        }
        if (start >= nums.length) {
            return 0;
        }
        int step2 = rob(nums, start + 2, cache);
        cache[start + 2] = step2;
        int step3 = rob(nums, start + 3, cache);
        cache[start + 3] = step3;
        int rob = nums[start] + Math.max(step2, step3);
        cache[start] = rob;
        return rob;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob2(new int[]{1, 2, 3, 1}) == 4);
        System.out.println(new Solution().rob2(new int[]{2, 7, 9, 3, 1}) == 12);
        System.out.println(new Solution().rob2(new int[]{1}) == 1);
        System.out.println(new Solution().rob2(new int[]{1, 1, 3, 4}) == 5);

    }

    public int rob2(int[] nums) {

        // 走到任意节点i: 只有两个选择： 偷还是不偷

        // 如果偷,截止到当前节点的值是: rob[i] = max(rob[i-1],rob[i-2]+nums[i])

        // 递归的起始条件: length = 1, nums[0]

        // length = 2, max[nums[1],nums[0]]

        int first = 0;
        int second = 0;
        for (int num : nums) {
            int tmp = second;
            second = Math.max(second, first + num);
            first = tmp;
        }

        return second;

    }

}
