package week_04.homework.s33_rotateArraySearch;

public class Solution {

    public int search(int[] nums, int k) {
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

}
