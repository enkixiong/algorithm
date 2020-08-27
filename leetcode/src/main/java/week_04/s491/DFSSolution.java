package week_04.s491;

import java.util.*;

public class DFSSolution {

    Map<Integer, Set<List<Integer>>> cache = new HashMap<>();


    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> results = new HashSet<>(dfs(nums, 0));
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[0]){
                results.addAll(dfs(nums,i));
            }
        }
        List<List<Integer>> lists = new ArrayList<>(results.size());
        for(List<Integer> list : results){
            if (list.size() > 1){
                lists.add(list);
            }
        }
        return lists;
    }

    private Set<List<Integer>> dfs(int[] nums, int current) {
        if(cache.containsKey(current)){
            return cache.get(current);
        }
        Set<List<Integer>> levelSet = new HashSet<>();
        levelSet.add(Collections.singletonList(nums[current]));
        for(int i = current+1; i < nums.length; i++){
            if(nums[i] >= nums[current]){
                if(cache.containsKey(i)){
                    continue;
                }
                Set<List<Integer>> set = dfs(nums,i);
                levelSet.addAll(set);
                for(List<Integer> list : set){
                    List<Integer> newList = new ArrayList<>(list.size()+1);
                    newList.add(nums[current]);
                    newList.addAll(list);
                    levelSet.add(newList);
                }
            }
        }
        return levelSet;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findSubsequences(new int[]{1,2,4,3,5}));
    }


}
