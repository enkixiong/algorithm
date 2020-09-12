package week_05.s214;

import com.alibaba.fastjson.JSON;

/**
 * 结果: 超过内存限制,PASS
 */
class DPSolution {
    public String shortestPalindrome(String s) {

        // 几种思路:
        // 1. 先将前面补上*号,表示可以任意匹配; 从最后开始找,找到*号时,将后面的字符复制过去; 最后将字串截断
        // 2. 从0开始拓展,拓展回文子串最右的位置,将剩余的子串复制到头部

        // 理论上来说,利用辅助空间,可以达到O(N)的复杂度

        // 第二种解法,更加容易想到,并且更好实现

        // dp[][] 表示的意思是: 以 [i:j]是否可以构成回文子串
        // 对于j == i --> true
        // 对于 j == i +1 --> char[j] == char[i]
        // 对于 剩下的, j --> i j > i && char[j] == char[i] && dp[i+1][j-1]

        if(s.length() < 2){
            return s;
        }

        char[] data = s.toCharArray();

        // 状态定义 dp[i][j] 表示 data[i:j]是否是回文子串
        boolean[][] dp = new boolean[data.length][data.length];
        // 初始化
        dp[0][0] = true;
        for(int i = 1; i < data.length; i++){
            dp[i][i] = true;
            dp[i-1][i] = data[i-1] == data[i];
        }

        for(int i = 2; i < data.length; i++){
            for(int j = i-2; j >= 0; j--){
                dp[j][i] = data[j] == data[i] && dp[j+1][i-1];
            }
        }

        int max = 0;
        for(int i = data.length-1; i > 0; i--){
            if(dp[0][i]){
                max = i;
                break;
            }
        }

        // 此时表明: [0,max]是最长的回文子串,需要copy的是(max,length)的子串
        int copyLength = data.length-1-max;
        char[] result = new char[data.length+copyLength];
        System.arraycopy(data, 0, result, copyLength, data.length);
        for(int i = 0; i < copyLength; i++){
            result[i] = data[data.length-1-i];
        }

        System.out.println(JSON.toJSONString(dp));

        return new String(result);
    }

    public static void main(String[] args) {
//        System.out.println(new DFSSolution2().shortestPalindrome("aacecaaa"));
        System.out.println(new DFSSolution2().shortestPalindrome("abcd"));
//        System.out.println(new DFSSolution2().shortestPalindrome("baabc"));
        System.out.println(new DFSSolution2().shortestPalindrome("ab"));
//        System.out.println(new DFSSolution2().shortestPalindrome("abb"));




    }
}
