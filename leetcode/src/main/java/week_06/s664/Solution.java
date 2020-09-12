package week_06.s664;

class Solution {

    public int strangePrinter(String s) {
        char[] main = s.toCharArray();
        int[][] dp = new int[main.length][main.length];
        for(int i = 0; i < main.length; i++){
            dp[i][i] = 1;
        }
        return cal(dp, main, 0, main.length-1);
    }

    private int cal(int[][] dp, char[] main, int i, int j){
        if(j < i){
            return 0;
        }
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int ans = cal(dp,main, i,j-1)+1;
        char target = main[j];
        for(int k = i; k < j; k++){
            if(main[k] != target){
                continue;
            }
            ans = Math.min(ans,cal(dp,main,i,k-1) + cal(dp,main,k,j-1));
        }
        dp[i][j] = ans;
        return ans;
    }
}
