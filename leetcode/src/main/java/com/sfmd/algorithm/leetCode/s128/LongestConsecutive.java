package com.sfmd.algorithm.leetCode.s128;

import java.util.HashSet;
import java.util.Set;

/**
 * 未排序数组中的最长连续子序列
 *
 * 要求: 时间复杂度: O(n)
 */
public class LongestConsecutive {

    /**
     * 使用O(n)的空间复杂度; O(n)的时间复杂度
     */
    public int longestConsecutive(int[] nums) {

        if (nums == null || nums.length == 0){
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;
        for (int num : nums) {
            if (set.contains(num-1)){
                continue;
            }
            int currentNum = num;
            while (set.contains(currentNum+1)){
                currentNum++;
            }
            max = Math.max(max, currentNum-num+1);
            set.remove(num);
        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}) == 4);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{100,  200, 1, 3, 2}) == 3);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{100,  200, 1,  2}) == 2);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{100,  200, 1}) == 1);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{1,3}) == 1);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{1}) == 1);
        System.out.println(new LongestConsecutive().longestConsecutive(new int[]{}) == 0);





    }


}
