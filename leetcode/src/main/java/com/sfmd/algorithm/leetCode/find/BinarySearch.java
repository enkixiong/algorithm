package com.sfmd.algorithm.leetCode.find;

/**
 * 二分查找
 */
public class BinarySearch {

    public int binarySearch(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        return binaryQueryWithLimit(nums, k, low, high);
    }

    private int binaryQueryWithLimit(int[] nums, int k, int low, int high) {
        while(low <= high){
            int middle = (low + high) >> 1;
            if (nums[middle] == k){
                return middle;
            }
            if (nums[middle] < k){
                low = middle+1;
            }else{
                high = middle-1;
            }
        }
        return -1;
    }

    public int binarySearchMinOffset(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        while(low <= high){
            int middle = (low + high) >> 1;
            if (nums[middle] >= k){
                high = middle-1;
            }else{
                low = middle+1;
            }
        }
        return low < nums.length && nums[low] == k ? low : -1;
    }

    public int binarySearchMaxOffset(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        while(low <= high){
            int middle = (high+low) >> 1;
            if (nums[middle] <= k){
                low = middle+1;
            }else{
                high = middle -1;
            }
        }
        return high < nums.length && nums[high] == k ? high : -1;
    }

    public int binarySearchFirstEqOrBigger(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        while(low <= high){
            int middle = (low + high) >> 1;
            if (nums[middle] >= k){
                high = middle-1;
            }else{
                low = middle+1;
            }
        }
        // 如果最小值已经超过边界, 则找不到
        return low >= nums.length || nums[low] < k  ? -1 : low;
    }

    public int binarySearchLastLittleOrEq(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        while(low <= high){
            int middle = (low + high) >> 1;
            if (nums[middle] > k){
                high = middle-1;
            }else{
                low = middle+1;
            }
        }
        // 如果最小值已经超过边界, 则找不到
        return high == 0 && nums[0] > k ? -1 : high;
    }

    /**
     * 循环有序数组二分查找方法
     * @param nums
     * @param k
     * @return
     */
    public int binarySearchCircle(int[] nums, int k){
        // 首先进行二分
        int left = 0;
        int right = nums.length -1;

        while(left <= right){
            // 如果整体有序,则进行正常二分
            if (nums[left] < nums[right]){
                return binaryQueryWithLimit(nums, k, left, right);
            }

            // 这里就是循环有序列表;
            int middle = (left+right) >> 1;
            if (nums[middle] == k){
                return middle;
            }
            // 二分以后, 一定会有一个区间是循环有序，一个是有序

            // 当右边有序时:
            if (nums[middle] < nums[right]){
                //检查右边是否包含,如果包含, 则直接退化为二分查找,退出
                if (nums[middle] < k && k <= nums[right]){
                    return binaryQueryWithLimit(nums,k,middle+1,right);
                }
                // 如果右侧不包含,则在左侧查找
                right = middle-1;
                continue;
            }

            // 处理左边有序的情况
            if (nums[left] <= k && k < nums[middle]){
                return binaryQueryWithLimit(nums,k,left,middle-1);
            }
            left = middle +1;
        }
        return -1;
    }

    public int binarySearchCircle2(int[] nums, int k){
        if (nums.length == 0){
            return -1;
        }
        // 找到旋转点
        int left = 0;
        int right = nums.length-1;

        int whirlPointer = 0;

        while(left <= right){
            if (nums[left] <= nums[right]){
                whirlPointer = left;
                break;
            }
            int middle = (left+right) >> 1;
            int middleLeft = (middle-1+nums.length)%nums.length;
            if (nums[middle] < nums[middleLeft]){
                whirlPointer = middle;
                break;
            }
            // 说明左侧有序
            if (nums[left] <= nums[middle]) {
                left = middle + 1;
            }else{
                right = middle - 1;
            }
        }

        int begin = 0;
        int end = nums.length-1;
        // 基于旋转点进行二分
        while (begin <= end){
            int middle = (begin+end) >> 1;
            int tmpMiddle = (middle + whirlPointer) % nums.length;
            if (nums[tmpMiddle] == k){
                return tmpMiddle;
            }
            if (nums[tmpMiddle] < k){
                begin = middle +1;
            }else{
                end = middle -1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, -1) == -1);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 1) == 0);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 2) == -1);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 3) == 1);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 4) == -1);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 14) == -1);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 15) == 8);
//        System.out.println(new BinarySearch().binarySearch(new int[]{1,3,5,7,8,9,11,13,15}, 16) == -1);
//
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{1,3,5,7,8,8,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{8,8,8,8,8,8,9,11,13,15}, 8) == 0);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{8,8,8,8,8,8,9,11,13,15}, 7) == -1);

//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,8,9,11,13,15}, 8) == 5);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,8,8,9,11,13,15}, 8) == 6);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,8,8,8,9,11,13,15}, 8) == 7);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,8,8,8,8,9,11,13,15}, 8) == 8);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,3,5,7,8,8,8,8,8,8,9,11,13,15}, 8) == 9);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{8,8,8,8,8,8}, 8) == 5);
//        System.out.println(new BinarySearch().binarySearchMaxOffset(new int[]{1,8,8,8,8,8,8}, 8) == 6);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{8,8,8,8,8,8,9,11,13,15}, 7) == -1);
//        System.out.println(new BinarySearch().binarySearchMinOffset(new int[]{8,8,8,8,8,8,9,11,13,15}, 18) == -1);

//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,8,8,8,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{8,8,8,8,8,8}, 8) == 0);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,8,8,8,8,8,8}, 8) == 1);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{8,8,8,8,8,8,9,11,13,15}, 7) == 0);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{8,8,8,8,8,8,9,11,13,15}, 18) == -1);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,9,11,13,15}, -1) == 0);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,9,11,13,15}, 1) == 0);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,9,11,13,15}, 4) == 2);
//        System.out.println(new BinarySearch().binarySearchFirstEqOrBigger(new int[]{1,3,5,7,8,8,9,11,13,15}, 5) == 2);


//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,9,11,13,15}, 8) == 4);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,9,11,13,15}, 8) == 5);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,8,9,11,13,15}, 8) == 6);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,8,8,9,11,13,15}, 8) == 7);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,8,8,8,9,11,13,15}, 8) == 8);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,8,8,8,8,9,11,13,15}, 8) == 9);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{8,8,8,8,8,8}, 8) == 5);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,8,8,8,8,8,8}, 8) == 6);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{8,8,8,8,8,8,9,11,13,15}, 7) == -1);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{8,8,8,8,8,8,9,11,13,15}, 18) == 9);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,9,11,13,15}, -1) == -1);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,9,11,13,15}, 1) == 0);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,9,11,13,15}, 4) == 1);
//        System.out.println(new BinarySearch().binarySearchLastLittleOrEq(new int[]{1,3,5,7,8,8,9,11,13,15}, 5) == 2);


//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{1}, 1) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{2}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{0}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{0,2}, 2) == 1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{2,0}, 2) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{2,0,1}, 2) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{2,0,1}, 1) == 2);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{0,1,2}, 2) == 2);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{0,1,2}, 1) == 1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 4) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 5) == 1);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 6) == 2);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 1) == 3);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 2) == 4);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 3) == 5);
//        System.out.println(new BinarySearch().binarySearchCircle(new int[]{4,5,6,1,2,3}, 0) == -1);


//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{1}, 1) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{2}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{0}, 1) == -1);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{0,2}, 0) == 0);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{0,2}, 2) == 1);
//        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{2,0}, 2) == 0);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{2,0}, 0) == 1);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{3,1}, 1) == 1);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{2,0,1}, 2) == 0);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{2,0,1}, 1) == 2);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{0,1,2}, 2) == 2);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{0,1,2}, 1) == 1);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 4) == 0);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 5) == 1);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 6) == 2);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 1) == 3);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 2) == 4);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 3) == 5);
        System.out.println(new BinarySearch().binarySearchCircle2(new int[]{4,5,6,1,2,3}, 0) == -1);

    }



}
