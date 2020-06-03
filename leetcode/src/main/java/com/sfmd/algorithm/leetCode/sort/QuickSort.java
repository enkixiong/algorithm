package com.sfmd.algorithm.leetCode.sort;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序的实现
 */
public class QuickSort {

    public void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }

    public void quickSort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }
//        System.out.println(String.format("quick sort, %d->%d", left,right));
        int i = partition(nums, left, right);
//        System.out.println(String.format("partition: %d->%d", i, nums[i]));
        quickSort(nums, left, i-1);
        quickSort(nums,i+1, right);
    }

    private int partition(int[] nums, int left, int right) {
        // 思路: 尝试让i 指向一个比选中值大的值;
        // 如果当前值比选中值小, 则交换i,j ==》
        // 什么时候i,j重叠 ==> 刚开始时 && 从left开始

        int pivot = nums[right];
        int i = left;
        for (int j = i; j < right; j++){
            if (nums[j] < pivot) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                i++;
            }
        }
        int tmp = nums[i];
        nums[i] = nums[right];
        nums[right] = tmp;
        return i;
    }

    public int[] smallestK(int[] nums, int k){
        if (nums.length == 0 || k == 0) {
            return new int[]{};
        }
        int left = 0;
        int right = nums.length-1;
        int kOffset = k -1;
        while(true){
            int findKth = partition(nums, left, right);
            if (findKth == kOffset){
                break;
            }
            if (findKth < kOffset){
                left = findKth+1;
            }else{
                right = findKth-1;
            }
        }
        return Arrays.copyOf(nums,k);
    }
//
//    int[] partition(int[] nums, int left, int right){
//        int pivot = nums[right];
//
//    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3,5,6,4,1,2,3,4,5,6,1,2,2,3,4,5,6};
//        int[] nums = new int[]{6,9,3,11,8};
        int[] nums = new int[]{11,10,12,9,8};
        new QuickSort().quickSort(nums);
        System.out.println(JSON.toJSONString(nums));

        System.out.println(JSON.toJSONString(new QuickSort().smallestK(nums,1)));
        System.out.println(JSON.toJSONString(new QuickSort().smallestK(nums,2)));

    }

}
