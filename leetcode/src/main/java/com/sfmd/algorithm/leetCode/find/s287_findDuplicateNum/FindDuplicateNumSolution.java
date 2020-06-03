package com.sfmd.algorithm.leetCode.find.s287_findDuplicateNum;

public class FindDuplicateNumSolution {

    public int findDuplicate(int[] nums) {
        int min = 1;
        int max = nums.length-1;
        while(true){
            int middle = (min+max)/2;
            if (existsInPartition(nums, min, middle)){
                if (min == middle){
                    return min;
                }
                max = middle;
            }else{
                min = middle + 1;
            }
        }
    }

    public boolean existsInPartition(int[] nums, int min, int max){
        if (min > max){
            return false;
        }
        int count = 0;
        for (int num : nums){
            if (num >= min && num <= max){
                count++;
            }
        }
        return count > (max-min+1);
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,1}));
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,1,2}));
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,1,3}));
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,1,2,3}));
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,1,2,3,4}));
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{3,1,3,4,2}) == 3);
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{1,3,4,2,2}) == 2);
        System.out.println(new FindDuplicateNumSolution().findDuplicate(new int[]{4,3,1,4,2}) == 4);
    }
}
