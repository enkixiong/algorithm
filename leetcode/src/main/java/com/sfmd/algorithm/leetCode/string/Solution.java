package com.sfmd.algorithm.leetCode.string;

import java.util.Arrays;
import java.util.Collection;

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        char[] min = new char[strs.length];
        char[] max = new char[strs.length];
        Arrays.fill(min, 'z');
        Arrays.fill(max, 'a');
        String minStr = new String(min);
        String maxStr = new String(max);
        for (String str : strs){
            minStr = str.compareTo(minStr) > 0 ? minStr : str;
            maxStr = maxStr.compareTo(str) > 0 ? maxStr: str;
        }

        int minLength = Math.min(minStr.length(), maxStr.length());
        for (int i = 0; i < minLength; i++){
            if (minStr.charAt(i) != maxStr.charAt(i)){
                return minStr.substring(0,i);
            }
        }
        return minStr.substring(0,minLength);
    }

    private int longestCommonPrefix(char[] arr1, int minLength, char[] arr2){
        for (int i = 0; i < minLength; i++){
            if (arr1[i] != arr2[i]){
                return i;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"flower","flow","flight"}).equals("fl"));
        System.out.println(new Solution().longestCommonPrefix(new String[]{"dog","racecar","car"}).equals(""));
        System.out.println(new Solution().longestCommonPrefix(new String[]{"","racecar","car"}).equals(""));
    }

}
