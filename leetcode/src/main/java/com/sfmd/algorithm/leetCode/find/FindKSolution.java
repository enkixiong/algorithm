package com.sfmd.algorithm.leetCode.find;

public class FindKSolution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        if (nums1.length < nums2.length){
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        // 边界值(空值)判断 复杂度:O(1)
        if ((nums2.length == 0)) {
            if (nums1.length % 2 == 0) {
                return (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0d;
            } else {
                return nums1[nums1.length / 2];
            }
        }

        int left1 = 0;
        int left2 = 0;
        int k = (nums1.length + nums2.length+1) / 2;

        boolean isEven = (nums1.length + nums2.length)%2 == 0;

        while(k > 1){
            int step = k/2-1;
            step = Math.max(step, 0);
            int tmpLeft1 = Math.min(left1+step, nums1.length -1);
            int temLeft2 = Math.min(left2+step, nums2.length - 1);
            if (nums1[tmpLeft1] < nums2[temLeft2]){
                // 边界值检测
                if (tmpLeft1 == nums1.length -1){
                    left2 = (nums1.length + nums2.length+1) / 2 - 1 - nums1.length;
                    return isEven ? (nums2[left2] + nums2[left2+1])/2.0 : nums2[left2];
                }
                k = k - (tmpLeft1 -left1 + 1);
                left1 = tmpLeft1 + 1;
            }else{
                // 边界值检测(本数组已经全部数完)
                if (temLeft2 == nums2.length -1){
                    left1 = (nums1.length + nums2.length+1) / 2 - 1 - nums2.length;
                    return isEven ? (nums1[left1] + nums1[left1+1])/2.0 : nums1[left1];
                }
                k = k - (temLeft2 -left2 + 1);
                left2 = temLeft2 + 1;
            }
        }

        // 奇数情况解决完成;
        if (!isEven){
            return Math.min(nums1[left1], nums2[left2]);
        }
        // 处理偶数的情况
        int candidate;
        int candidate2;

        if (nums1[left1] < nums2[left2]){
            candidate = nums1[left1];
            int nextCandidateIndex = left1+1;
            candidate2 = Integer.min(nums2[left2],nextCandidateIndex == nums1.length ? Integer.MAX_VALUE : nums1[left1+1]);
        }else{
            candidate = nums2[left2];
            int nextCandidateIndex = left2+1;
            candidate2 = Integer.min(nums1[left1],nextCandidateIndex == nums2.length ? Integer.MAX_VALUE : nums2[left2+1]);
        }
        return (candidate+candidate2)/2.0 ;
    }

    public static void main(String[] args) {
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{}, new int[]{}) == 0);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1}, new int[]{}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 1}, new int[]{}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{}) == 2);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{}, new int[]{1}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1}, new int[]{1}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{2}, new int[]{1}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 1}, new int[]{1}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{1}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{1}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{}, new int[]{1, 2}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1}, new int[]{1, 2}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 1}, new int[]{1, 2}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 2}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{1, 2}) == 2);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3}) == 2);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1}, new int[]{1, 2, 3}) == 1.5d);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 1}, new int[]{1, 2, 3}) == 1);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 2, 3}) == 2);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{1, 2, 3}) == 2);

        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4}) == 2.5); // TODO
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5}) == 3);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6}) == 3.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{4}, new int[]{1, 2, 3}) == 2.5); // TODO
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{4, 5}, new int[]{1, 2, 3}) == 3);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{4, 5, 6}, new int[]{1, 2, 3}) == 3.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}) == 5.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 4, 6, 8}, new int[]{3, 5, 7, 9, 10}) == 5.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4,5,6, 7, 8, 9, 10}) == 5.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5,6, 7, 8, 9, 10}) == 5.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}) == 5.5);
//
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) == 2.5);
        //
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 4}, new int[]{2, 3}) == 2.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{-1, 3}) == 1.5);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{-1, 3}, new int[]{1, 2}) == 1.5);

        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1,3,4,9}, new int[]{1,2,3,4,5,6,7,8,9}) == 4);
        System.out.println(new FindKSolution().findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6,7}) == 4);

    }


}
