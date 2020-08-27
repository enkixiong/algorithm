package week_04.s491;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Solution {

    Map<Integer,List<List<Integer>>> cache = new HashMap<>();

    public List<List<Integer>> findSubsequences(int[] nums) {

        if(nums.length < 2){
            return Collections.emptyList();
        }

        // 最长子序列的长度: DP解法
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] <= nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }


        System.out.println(JSON.toJSONString(dp));
        // 回溯产生结果

        for(int i = nums.length-1; i >= 0; i--){
            backtrace(dp,nums,i, dp[i]);
        }

        Set<List<Integer>> results = new HashSet<>();
        for(List<List<Integer>> level : cache.values()){
            for(List<Integer> result : level){
                if(result.size() > 1){
                    results.add(result);
                }
            }
        }

        System.out.println(results.size());

        return new ArrayList<>(results);

    }

    // 回溯
    private List<List<Integer>> backtrace(int[] dp, int[] nums, int offset, int target){
        if(cache.containsKey(offset)){
            return cache.get(offset);
        }
        List<List<Integer>> currentLevelResults = new ArrayList<>();
        for(int i = offset-1; i >= 0; i--){
            if(nums[i] <= nums[offset]){
                List<List<Integer>> preResults = backtrace(dp, nums, i, target-1);
                for(List<Integer> list : preResults){
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(nums[offset]);
                    currentLevelResults.add(newList);
                }
                currentLevelResults.addAll(preResults);
            }
        }
        currentLevelResults.add(Collections.singletonList(nums[offset]));
        cache.put(offset,currentLevelResults);
        return currentLevelResults;
    }

    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{1})));
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{1,2})));
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{2,1,3})));
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{4, 6, 7, 7})));
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{1,1,1,1})));
//        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1})));
        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{1,2,3,1,1,1})));
        System.out.println(JSON.toJSONString(new Solution().findSubsequences(new int[]{3,2,1,2,3})));

    }
}
