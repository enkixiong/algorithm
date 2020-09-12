package week_06.s76;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public String minWindow(String s, String t) {
        // 滑动窗口的问题

        // 1. 包含所有的字符, 说明不关心排序,只需要字符数&字符能够统一就OK

        // 左指针: 当前子串的开始位置(包含)
        // 右指针: 当前子串的结束位置(不包含)

        // 右指针不断向右拓展,拓展到满足条件截止;
        // 左指针尝试缩小子串的范围, 一直到子串不满足条件,那么[left,right)就是当前活动窗口找到的最佳位置,继续下一轮迭代
        Map<Character,Integer> countMap = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            int count = countMap.getOrDefault(c,0)+1;
            countMap.put(c,count);
        }

        // 子串的定义: [left-1, right-1]
        int left = 0;
        int right = 0;

        int[] result = new int[]{0,Integer.MAX_VALUE};
        Map<Character,Integer> currentCountMap = new HashMap<>();
        int needCount = countMap.size();
        // 结果子串是:[left,right]
        while(right < s.length()){
            char c = s.charAt(right++);
            if(!countMap.containsKey(c)){
                continue;
            }
            int currentCount = currentCountMap.getOrDefault(c,0)+1;
            currentCountMap.put(c,currentCount);
            if(currentCount == countMap.get(c)){
                needCount--;
            }
            if(needCount > 0){
                continue;
            }
            while(needCount == 0){
                char remove = s.charAt(left++);
                if(!currentCountMap.containsKey(remove)){
                    continue;
                }
                currentCountMap.put(remove, currentCountMap.get(remove)-1);
                if(currentCountMap.get(remove) < countMap.get(remove)){
                    needCount++;
                }
            }
            // 此时，子串就是 [left-1,right)
            if(right - left < result[1] - result[0]){
                result[0] = left;
                result[1] = right;
            }
        }

        return result[1] == Integer.MAX_VALUE ? "":s.substring(result[0]-1, result[1]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("ADOBECODEBANC","ABC"));
        System.out.println(new Solution().minWindow("ADOBECODEBANC","ABCG"));

    }

}
