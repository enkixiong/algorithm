package com.sfmd.algorithm.leetCode.s621;

import java.util.Arrays;

public class Solution {


    public int leastInterval(char[] tasks, int n) {
        int count = tasks.length;
        int costs = 0;
        int[] counts = new int[26];
        for (char task : tasks) {
            int offset = task - 0b01000001; // A的ASCII码是 0b01000001
            counts[offset] = counts[offset]+1;
        }

        int max = counts[25];
        while(count > 0){
            // 进行升序排列
            Arrays.sort(counts);
            // 消费最大的任务列表
            max = counts[25];

            // 需要max*n的时间片需要从其他选项处扣除
            int needFill = (max-1)*n;
            // 增加时间片
            costs += needFill;
            if (count < costs){
                break;
            }
            for (int i = 25; i >=0; i--){
                int fill = Math.min(needFill, counts[i]);
                counts[i] -= fill;
                needFill -= fill;
                count -= fill;
                if (needFill == 0){
                    break;
                }
            }
        }

//        if (n != 0 && costs%n == 0){
//            costs -= max-1;
//        }

        return costs;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char task : tasks) {
            int offset = task - 0b01000001; // A的ASCII码是 0b01000001
            counts[offset] = counts[offset]+1;
        }
        Arrays.sort(counts);
        int maxVal = counts[25]-1;
        int idleSlots = maxVal*n;
        for (int i = 24; i >= 0 & counts[i] > 0; i--){
            idleSlots -= Math.min(maxVal, counts[i]);
        }
        return idleSlots > 0 ? idleSlots+tasks.length : tasks.length;
    }


        public static void main(String[] args) {
        System.out.println(new Solution().leastInterval2(new char[]{},2) == 0);
        System.out.println(new Solution().leastInterval2(new char[]{'A'},2) == 1);
        System.out.println(new Solution().leastInterval2(new char[]{'A','A'},2) == 4);
        System.out.println(new Solution().leastInterval2(new char[]{'A','B'},2) == 2);
        System.out.println(new Solution().leastInterval2(new char[]{'A','B','A'},2) == 4);
        System.out.println(new Solution().leastInterval2(new char[]{'A','B','A','B'},2) == 5);
        System.out.println(new Solution().leastInterval2(new char[]{'A','A','B'},2) == 4);
        System.out.println(new Solution().leastInterval2(new char[]{'A','A','B'},1) == 3);
        System.out.println(new Solution().leastInterval2(new char[]{'A','A','B'},0) == 3);
        System.out.println(new Solution().leastInterval2(new char[]{'A','A','A','B','B','B'},2) == 8);
    }


}
