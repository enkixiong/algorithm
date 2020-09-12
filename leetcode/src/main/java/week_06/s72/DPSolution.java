package week_06.s72;

public class DPSolution {

    public int minDistance(String word1, String word2) {

        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        int[][] dp = new int[charArray1.length+1][charArray2.length+1];

        // 自左到右进行扫描
        for(int j = 0; j < charArray2.length; j++){
            dp[0][j] = j;
        }
        for(int i = 0; i < charArray1.length; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i <= charArray1.length; i++){
            for(int j = 1; j <= charArray2.length; j++){
                if(charArray1[i-1] == charArray2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }

        return dp[charArray1.length][charArray2.length];
    }

}
