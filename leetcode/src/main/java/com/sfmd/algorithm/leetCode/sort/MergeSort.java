package com.sfmd.algorithm.leetCode.sort;

import com.alibaba.fastjson.JSON;

public class MergeSort {

    int[] mergeSort(int[] sort){
        int[] sorted = mergeSort(sort,0, sort.length-1);
        System.out.println(JSON.toJSONString(sorted));
        return sorted;
    }

    int[] mergeSort(int[] sort, int left, int right){
        if (left > right){
            return new int[]{};
        }
        if (left == right){
            return new int[]{sort[left]};
        }
        int middle = (right+left)/2;
        int[] leftSorted = mergeSort(sort,left, middle);
        int[] rightSorted = mergeSort(sort,middle+1, right);
        int[] sorted = new int[right-left+1];
        int leftOffset = 0,rightOffset = 0,offset = 0;
        while(leftOffset < leftSorted.length && rightOffset < rightSorted.length){
            if (leftSorted[leftOffset] <= rightSorted[rightOffset]){
                sorted[offset] = leftSorted[leftOffset];
                leftOffset++;
            }else{
                sorted[offset] = rightSorted[rightOffset];
                rightOffset++;
            }
            offset++;
        }
        if (leftOffset == leftSorted.length){
            while(rightOffset < rightSorted.length){
                sorted[offset] = rightSorted[rightOffset];
                rightOffset++;
                offset++;
            }
        }else{
            while(leftOffset < leftSorted.length){
                sorted[offset] = leftSorted[leftOffset];
                leftOffset++;
                offset++;
            }
        }
        return sorted;
    }


    /**
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     */
    public void merge(int[] a, int m, int[] b, int n) {
        int endOffset = m+n-1;
        m -= 1;
        n -= 1;
        while (n >= 0){
            if (m < 0) {
                for (int i = n; i >= 0; i--) {
                    a[i] = b[i];
                }
                break;
            }
            if (a[m] > b[n]){
                a[endOffset] = a[m];
                m -= 1;
            }else{
                a[endOffset] = b[n];
                n -= 1;
            }
            endOffset--;
        }
    }

    public static void main(String[] args) {
        new MergeSort().mergeSort(new int[]{1,2,3,4,5,6});
        new MergeSort().mergeSort(new int[]{});
        new MergeSort().mergeSort(new int[]{1});
        new MergeSort().mergeSort(new int[]{1,1});
        new MergeSort().mergeSort(new int[]{1,2});
        new MergeSort().mergeSort(new int[]{2,1});
    }
}
