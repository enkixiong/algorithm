package week_05.s159;

import com.alibaba.fastjson.JSON;

public class Solution {

    public int lengthOfLongestSubstringTwoDistinct(String s) {

        if (s.length() == 0) {
            return 0;
        }
        char[] array = s.toCharArray();

        int[][] dp = new int[s.length()][6];
        // 第二维: 当前值计数(连续),当前连续总计, 当前字符, 上一个字符的计数(连续), 上一个字符的连续总计, 上一个字符
        dp[0] = new int[]{1, 1, array[0], 0, 0, -1};
        int max = 1;
        for (int i = 1; i < array.length; i++) {
            // 当前字符
            dp[i][2] = array[i];
            if (dp[i][2] == dp[i - 1][2]) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
                dp[i][3] = dp[i - 1][3];
                dp[i][4] = dp[i - 1][4];
                dp[i][5] = dp[i - 1][5];
            } else {
                boolean sticky = dp[i][2] == dp[i - 1][5];
                dp[i][0] = 1;
                dp[i][1] = sticky ? dp[i - 1][4] + 1 : 1;
                dp[i][3] = dp[i - 1][0];
                dp[i][4] = sticky ? dp[i - 1][1] : dp[i - 1][0];
                dp[i][5] = dp[i - 1][2];
            }
            max = Math.max(max, dp[i][1] + dp[i][4]);
        }

        return max;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("acaacacaaaaaacaeb"));
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("abaccc"));
    }

}
