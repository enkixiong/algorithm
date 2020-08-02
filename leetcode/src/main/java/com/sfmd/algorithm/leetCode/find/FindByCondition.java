package com.sfmd.algorithm.leetCode.find;

import java.util.Arrays;

public class FindByCondition {


    public int findBestValue(int[] arr, int target) {
        // 题目的意图: 不断尝试逼近target;
        // 情况1: sum(arr) < target --> 不做任何处理
        // 情况2: sum(arr) == target
        // 情况3: sum(arr) > target
            // 情况3.1: avg(arr) <= min(arr)
            // 情况3.2: avg(arr) >= max(arr) ==> 属于情况1&情况2
            // 情况3.3: avg(sum) 在数组中间

        // 排序
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // 当前长度
            int currentLength = arr.length - i;

            // 计算平均值
            int avg = target / currentLength;
            // 四舍五入
            avg = (target % currentLength) <= (currentLength >> 1) ? avg : avg + 1;

            // 当平均值 <= 当前最小元素时,返回
            if (avg <= arr[i]) {
                return avg;
            }

            // 维护新的目标值
            target -= arr[i];
        }

        // 返回最大元素
        return arr[arr.length - 1];

    }

    public int findBestValue2(int[] arr, int target) {

        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;

        for (int num : arr) {
            sum += num;
            min = Math.min(min,num);
            max = Math.max(max,num);
        }
        // sum(arr) <= target的情况, 不做任何处理;直接返回最大元素
        if (sum <= target){
            return max;
        }


        int startIndex = 0;
        while(true){
            min = insertSort(arr,startIndex);
            int length = (arr.length-startIndex);
            int avg = target / (length);
            avg = (target % length) <= (length >> 1) ? avg : avg + 1;
            if (avg <= min){
                return avg;
            }

            target -= min;
            startIndex++;
        }
    }

    public int insertSort(int[] arr, int startIndex){
        for (int i = startIndex+1; i < arr.length; i++){
            if (arr[i] < arr[startIndex]){
                int tmp = arr[i];
                arr[i] = arr[startIndex];
                arr[startIndex] = tmp;
            }
        }
        return arr[startIndex];
    }


    public static void main(String[] args) {
        System.out.println(new FindByCondition().findBestValue2(new int[]{4, 9, 3}, 10) == 3);
        System.out.println(new FindByCondition().findBestValue2(new int[]{2, 3, 5}, 10) == 5);
        System.out.println(new FindByCondition().findBestValue2(new int[]{60864, 25176, 27249, 21296, 20204}, 56803) == 11361);
        System.out.println(new FindByCondition().findBestValue2(new int[]{48772, 52931, 14253, 32289, 75263}, 40876) == 8175);
        System.out.println(new FindByCondition().findBestValue2(new int[]{1547, 83230, 57084, 93444, 70879}, 71237) == 17422); // 17422

    }

}
