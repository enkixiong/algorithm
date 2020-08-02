package com.sfmd.algorithm.leetCode.meeting;

import java.util.Arrays;
import java.util.Comparator;

class Solution2 {

    public int findMin(int[] info, int[][] info2){

        // 1. 从info2中抽取中餐,获取最高的美味值
        Pair[] noon = new Pair[info[0]];
        int maxTaste1 = Integer.MIN_VALUE;
        for (int i = 0; i < info[0]; i++) {
            noon[i] = new Pair(info2[i][0], info2[i][1]);
            maxTaste1 = Math.max(maxTaste1, noon[i].taste);
        }


        sort(noon);
        // 2. 冲info2中抽取晚餐, 获取最高的美味值
        Pair[] dinner = new Pair[info[1]];
        int maxTaste2 = Integer.MIN_VALUE;
        for (int i = 0; i < info[1]; i++) {
            dinner[i] = new Pair(info2[info[0]+i][0], info2[info[0]+i][1]);
            maxTaste2 = Math.max(maxTaste2, dinner[i].taste);
        }
        sort(dinner);

        // 如果1,2最高的热量相加 < info[2] return -1;
        if (maxTaste1 + maxTaste2 < info[2]){
            return -1;
        }

        // 按照 noon[i][1] / noon[i][0] 进行降序排序
        // 按照 dinner[i][1] / dinner[i][0] 进行降序排序
        int index1 = info[0]-1;
        int index2 = info[1]-1;


        int result = 0;

        // 从最大端开始组合寻找, >info[3] 时,最小的能量， 一直寻找到美味值相加 < T时,退出循环
        // 贪心算法:
        if (maxTaste1 > info[2]){
            // cal min;
        }
        if (maxTaste2 > info[2]){
            // cal min;
        }


        return result;

    }

    private void sort(Pair[] noon) {
        Arrays.sort(noon, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.hot * o2.taste == o1.taste * o2.hot){
                    return 0;
                }
                return 1.0f*o1.taste/o1.hot > 1.0f*o2.taste/o2.hot ? 1 : -1;
            }
        });
    }

    private static class Pair {
        int hot;// 热量
        int taste;// 美味度

        public Pair(int hot, int taste) {
            this.hot = hot;
            this.taste = taste;
        }
    }

    public static void main(String[] args) {
//        int K=6;
//        int N=3;
//        int[] num = {4,2,6};
//
//        int step = K;
//
//        int back = 0;
//
//
//        for (int i = 0; i < N; ++i) {
//            if (num[i] == step) {
//                System.out.println("paradox");
//            } else if (num[i] < step){
//                step = step - num[i];
//            } else {
//                step = num[i] - step;
//                back++;
//            }
//        }
//
//        System.out.println(step + " " + back);

        fly(6,3, new int[]{4,2,6});
        fly(10,4, new int[]{6,3,3,3});


    }


    public static void fly(int dis, int step,int[] nums){
        int current = 0;
        int back = 0;
        for (int i = 0; i < step; i++) {
            current += nums[i];
            if (current % dis == 0){
                System.out.println("paradox");
                return;
            }
            int isBack = current / dis;
            back += isBack;
            if (isBack != 0) {
                current = dis - current % dis;
            }
        }
        System.out.println(String.format("%d %d", dis-current, back));
    }



}
