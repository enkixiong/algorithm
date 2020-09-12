package week_06.s72;

class DFSSolution {

    char[] charArray1;
    char[] charArray2;

    public int minDistance(String word1, String word2) {

        charArray1 = word1.toCharArray();
        charArray2 = word2.toCharArray();

        return dfs(0,0);

    }

    private int dfs(int offset1, int offset2){
        // terminator:
        if(offset1 == charArray1.length || offset2 == charArray2.length){
            return charArray1.length+charArray2.length - offset1 -offset2;
        }

        // handle current level
        if(charArray1[offset1] == charArray2[offset2]){
            // drill down
            return dfs(offset1+1, offset2+1);
        }else{
            return 1 + Math.min(
                    Math.min(dfs(offset1+1, offset2), dfs(offset1, offset2+1)),
                    dfs(offset1+1, offset2+1)
            );
        }
    }

}
