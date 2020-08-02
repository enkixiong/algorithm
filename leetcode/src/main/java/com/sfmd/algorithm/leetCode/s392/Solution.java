package com.sfmd.algorithm.leetCode.s392;

class Solution {
    public boolean isSubsequence(String s, String t) {
        int sPtr = 0;
        int tPtr = 0;
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        while(sPtr < sCharArray.length && tPtr < tCharArray.length){
            if (sCharArray[sPtr] == tCharArray[tPtr++]){
                sPtr++;
            }
        }

        return sPtr == sCharArray.length;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isSubsequence("abc","ahbgdc"));
        System.out.println(new Solution().isSubsequence("acb","ahbgdc"));
        System.out.println(new Solution().isSubsequence("axc","ahbgdc"));
        System.out.println(new Solution().isSubsequence("","ahbgdc"));
    }
}
