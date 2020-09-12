package week_06.s72;

import java.util.Arrays;

public class MemoSolution {

    char[] charArray1;
    char[] charArray2;

    int[][] memo;

    public int minDistance(String word1, String word2) {

        charArray1 = word1.toCharArray();
        charArray2 = word2.toCharArray();
        memo = new int[charArray1.length][charArray2.length];
        for(int i = 0; i < charArray1.length; i++){
            Arrays.fill(memo[i],-1);
        }
        return dfs(0,0);
    }

    private int dfs(int offset1, int offset2){
        // terminator:
        if(offset1 == charArray1.length || offset2 == charArray2.length){
            return charArray1.length+charArray2.length - offset1 -offset2;
        }

        if(memo[offset1][offset2] != -1){
            return memo[offset1][offset2];
        }


        // handle current level
        if(charArray1[offset1] == charArray2[offset2]){
            // drill down
            memo[offset1][offset2] =  dfs(offset1+1, offset2+1);
        }else{
            memo[offset1][offset2] =  1 + Math.min(
                    Math.min(dfs(offset1+1, offset2), dfs(offset1, offset2+1)),
                    dfs(offset1+1, offset2+1)
            );
        }

        return memo[offset1][offset2];
    }

}
