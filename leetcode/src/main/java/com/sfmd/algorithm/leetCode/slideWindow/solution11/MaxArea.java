package com.sfmd.algorithm.leetCode.slideWindow.solution11;

public class MaxArea {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxArea = 0;
        while(left < right){
            int currentArea = (height[left] < height[right] ? height[left] : height[right]) * (right-left);
            maxArea = maxArea > currentArea ? maxArea : currentArea;
            if (height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new MaxArea().maxArea(new int[]{1,2}));
        System.out.println(new MaxArea().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(new MaxArea().maxArea(new int[]{2,3,10,5,7,8,9}));
        System.out.println(new MaxArea().maxArea(new int[]{1,9,9,10}));
        System.out.println(new MaxArea().maxArea(new int[]{1,1,100,1,1}));
        System.out.println(new MaxArea().maxArea(new int[]{1,1,100,100,1,1}));
        System.out.println(new MaxArea().maxArea(new int[]{1,2,4,3}));
    }

}
