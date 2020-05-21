package com.sfmd.algorithm.leetCode.solution5;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("a").equals("a"));
        System.out.println(solution.longestPalindrome("").equals(""));
        System.out.println(solution.longestPalindrome(null) == null);
        System.out.println(solution.longestPalindrome("ab").equals("a"));
        System.out.println(solution.longestPalindrome("aa").equals("aa"));
        System.out.println(solution.longestPalindrome("aab").equals("aa"));
        System.out.println(solution.longestPalindrome("acbbca").equals("acbbca"));
        System.out.println(solution.longestPalindrome("acbca").equals("acbca"));
        System.out.println(solution.longestPalindrome("acbbcad").equals("acbbca"));
        System.out.println(solution.longestPalindrome("acbcad").equals("acbca"));
        System.out.println("acbbca"+"-"+solution.longestPalindrome("acbbca")+"-"
                + solution.longestPalindrome("acbbca").equals("acbbca"));
        System.out.println("acbca"+"-"+solution.longestPalindrome("acbca")+"-"
                + solution.longestPalindrome("acbca").equals("acbca"));
        System.out.println("acbbcad"+"-"+solution.longestPalindrome("acbbcad")+"-"
                + solution.longestPalindrome("acbbcad").equals("acbbca"));
        System.out.println("acbcad"+"-"+solution.longestPalindrome("acbcad")+"-"
                + solution.longestPalindrome("acbcad").equals("acbca"));
        System.out.println("abbc"+"-"+solution.longestPalindrome("abbc")+"-"
                + solution.longestPalindrome("abbc").equals("bb"));
        System.out.println("ccc"+"-"+solution.longestPalindrome("ccc")+"-"
                + solution.longestPalindrome("ccc").equals("ccc"));
        System.out.println("cccc"+"-"+solution.longestPalindrome("cccc")+"-"
                + solution.longestPalindrome("cccc").equals("cccc"));
        System.out.println("ccccc"+"-"+solution.longestPalindrome("ccccc")+"-"
                + solution.longestPalindrome("ccccc").equals("ccccc"));
        System.out.println("cccccc"+"-"+solution.longestPalindrome("cccccc")+"-"
                + solution.longestPalindrome("cccccc").equals("cccccc"));

    }

    public String longestPalindrome(String s) {

        // 边界值检测
        if (null == s || 0 == s.length() || 1 == s.length()){
            return s;
        }

        int length = s.length();
        char[] charArray = s.toCharArray();

        // 构建哨兵
        int[] info = new int[]{0,0};
        if (charArray[0] == charArray[1]){
            info[1] = 1;
        }

        int[] tmp = new int[]{0,0};
        // 开始迭代寻找
        for (int i = 1; i <= length-2; i++){

            tmp[0] = i;
            tmp[1] = i;
            if (findTmp(charArray, info, tmp) && info[1] == length){
                break;
            }
            if (charArray[i] == charArray[i+1]){
                tmp[0] = i;
                tmp[1] = i+1;
                findTmp(charArray, info, tmp);
                if (findTmp(charArray, info, tmp) && info[1] == length){
                    break;
                }
            }
        }
        return s.substring(info[0],info[1]+1);
    }

    private boolean findTmp(char[] charArray, int[] info, int[] tmp) {
        longestPalindromeInfo(charArray, tmp);
        int tmpLength = tmp[1] - tmp[0];
        if (tmpLength > info[1] - info[0]){
            info[0] = tmp[0];
            info[1] = tmp[1];
            return true;
        }
        return false;
    }

    /**
     *
     * @param charArray :待寻找的回文子串
     * @param position : 起始信息
     */
    private void longestPalindromeInfo(char[] charArray, int[] position){
        int begin = position[0];
        int end = position[1];

        int length = charArray.length;
        while(true){
            begin--;
            end++;
            if (begin < 0 || end >= length || charArray[begin] != charArray[end]){
                position[0] = begin + 1;
                position[1] = end -1;
                break;
            }
        }
    }

}
