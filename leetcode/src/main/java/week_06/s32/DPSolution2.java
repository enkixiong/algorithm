package week_06.s32;

public class DPSolution2 {

    public int longestValidParentheses(String s) {
        char[] main = s.toCharArray();
        // 初始化DP
        int[] dp = new int[main.length + 1];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i <= main.length; i++) {
            char current = main[i - 1];
            if (current == '(') {
                continue;
            }
            // 期待与 ) 想匹配的 ( 出现的位置; 两种情况: 嵌套/非嵌套; 非嵌套的情况下可以理解为嵌套为0
            int begin = (i - 1) - dp[i - 1] - 1; // i-1为main中的主串; dp[i-1]标识当前是多少个有效字符串的结尾
            if (begin < 0 || main[begin] == ')') {
                // 不能正确闭合
                continue;
            }

            dp[i] = dp[i - 1] + 2; // 找到了有效位置,则进行闭合操作
            // 期待与上一个 ) 黏起来; 与上一个 ) 黏起来的前提是: 当前 ) 能够正确闭合
            int preEnd = begin - 1;
            if (preEnd >= 0 && main[preEnd] == ')') {
                dp[i] += dp[preEnd + 1];
            }
            max = Math.max(dp[i], max);

        }
        return max;
    }
}
