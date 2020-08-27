package week_04.s698;

import java.util.Arrays;

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 思路:
        // 1. nums的总和,是否可以被K整除 O(n)
        // 2. 对nums进行排序;
        // 3. 从最右侧开始DFS
        // 4. 确定开始进行DFS的下标范围 nums[0,right]: > nums/k 失败; == nums/k --> right--;
        // 5. 取最大值,并且标记为当前值已经被选择
        // 6. DFS(nums/k - )


        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        if (sum % k != 0){
            return false;
        }

        int everyCellNum = sum / k;
        Arrays.sort(nums);
        int right = nums.length-1;

        if (nums[right] > everyCellNum){
            return false;
        }

        boolean[] visited = new boolean[right+1];
        return helper(nums, visited, everyCellNum,everyCellNum, k);

        // 怎么快速检测 一个数组中,是否有合为 everyCellNum 的子集合？
        // 1. 取最大值, 迭代问
    }

    private boolean helper(int[] nums, boolean[] visited, int target, int every, int k) {
        if(target == 0 && k == 1){
            return true;
        }
        if(target == 0){
            return helper(nums, visited, every, every, k-1);
        }
        for(int i = visited.length-1; i >= 0; i--){
            if(visited[i] || nums[i] > target){
                continue;
            }
            visited[i] = true;
            if(helper(nums, visited, target - nums[i], every, k)){
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1},4));
    }




}
