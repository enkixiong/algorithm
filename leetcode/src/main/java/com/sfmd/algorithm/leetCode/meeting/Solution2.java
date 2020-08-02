package com.sfmd.algorithm.leetCode.meeting;

class Solution2 {

    public int findMin(int[] info, int[][] info2){

        // 1. 从info2中抽取中餐,获取最高的美味值
        int[][] noon = new int[info[0]][2];
        // 2. 冲info2中抽取晚餐, 获取最高的美味值
        int[][] dinner = new int[info[1]][2];
        // 如果1,2最高的热量相加 < info[2] return -1;

        // 按照 noon[i][1] / noon[i][0] 进行降序排序
        // 按照 dinner[i][1] / dinner[i][0] 进行降序排序

        int result = 0;

        // 从最大端开始组合寻找, >info[3] 时,最小的能量， 一直寻找到美味值相加 < T时,退出循环
        // 贪心算法:


        return result;

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
