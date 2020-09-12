package week_06.s32;

class DPSolution {

    public int longestValidParentheses(String s) {

        // 遇见左括号的决策: 当前未关闭的左括号+1
        // 遇见右括号时,是否有可关闭的左括号,如果没有，这里就是一个坏字符; 这里不可黏合
        // 怎么表示不可黏合? 可黏合的为0
        // () [0,1], [2,0]
        // ())[0,1], [2,0],[0,0]
        // ()() [0,1],[2,0],[0,1],[4,0]
        // ((())) [0,1],[0,2],[0,3], [2,2],[4,1],[6,0]

        char[] main = s.toCharArray();
        int[][] dp = new int[main.length+1][2];
        // dp[][0] 表示当前可黏合的数量 dp[][1]表示未关闭的左括号数
        for(int i = 1; i <= main.length; i++){
            if(main[i-1] == '('){
                dp[i][0] = 0;
                dp[i][1] = dp[i-1][1]+1;
            }else{
                // 有未闭合的
                if(dp[i-1][1] != 0){
                    dp[i][0] = dp[i-1][0]+2;
                    dp[i][1] = dp[i-1][1]-1;
                    int stick = i-dp[i][0];
                    // 当前是否可以黏合
                    if (stick >= 1 && main[stick-1] == ')'){
                        dp[i][0] += dp[stick][0];
                    }
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= main.length; i++){
            max = Math.max(dp[i][0], max);
        }

        return max;
    }

}
