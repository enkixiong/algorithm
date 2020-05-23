package com.sfmd.algorithm.leetCode.string.solution78;

import java.util.HashMap;
import java.util.Map;

public class SlideWindow {

    public String minWindow(String s, String t) {

        // 边界值检测
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        Map<Character, Integer> tCountMap = new HashMap<>(charArrayT.length);
        for (char charT : charArrayT){
            tCountMap.put(charT,tCountMap.getOrDefault(charT,0)+1);
        }
        int missingCharCount = tCountMap.size();

        int left = -1;
        int right = -1;

        int[] result = new int[]{0,Integer.MAX_VALUE}; //[begin,end)

        int rightLimit = charArrayS.length -1;
        while(right < rightLimit){
            right++;
            char charRight = charArrayS[right];
            if (!tCountMap.containsKey(charRight)){
                continue;
            }
            int newValue = tCountMap.get(charRight)-1;
            tCountMap.put(charRight, newValue);
            if (newValue == 0){
                missingCharCount--;
            }
            if (missingCharCount != 0){
                continue;
            }

            while(left < right){
                left++;
                char charLeft = charArrayS[left];
                if (!tCountMap.containsKey(charLeft)){
                    continue;
                }
                int incrementNum = tCountMap.get(charLeft)+1;
                tCountMap.put(charLeft,incrementNum);
                if (incrementNum > 0){
                    missingCharCount++;
                }
                if (missingCharCount > 0){
                    if (result[1] - result[0] > right-left){
                        result[0] = left;
                        result[1] = right;
                    }
                    break;
                }
            }
        }
        if (result[1] == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(result[0],result[1]+1);
    }

    public static void main(String[] args) {
//        System.out.println(new SlideWindow().minWindow("ADOBECODEBANC","ABC"));
        System.out.println(new SlideWindow().minWindow("ADOBECODEBANC",""));
        System.out.println(new SlideWindow().minWindow("ADOBECODEBANC","D"));
        System.out.println(new SlideWindow().minWindow("ADOBECODEBANC","G"));
        System.out.println(new SlideWindow().minWindow("ABA","AA"));
        System.out.println(new SlideWindow().minWindow("bbaa","aba"));
        System.out.println(new SlideWindow().minWindow("acbbaca","aba"));
        System.out.println(new SlideWindow().minWindow("acbdbaab", "aabd"));
    }

}
