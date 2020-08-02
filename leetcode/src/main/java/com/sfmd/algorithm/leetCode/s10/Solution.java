package com.sfmd.algorithm.leetCode.s10;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Solution {

    public boolean isMatch(String mainStr, String patternStr) {
        Map<Pair, Boolean> cache = new HashMap<>();
        return isMatch(mainStr.toCharArray(), 0, patternStr.toCharArray(), 0, cache);
    }

    private boolean isMatch(char[] main, int mainIndex, char[] pattern, int patternIndex, Map<Pair, Boolean> cache) {
        Pair pair = new Pair(mainIndex,patternIndex);
        if (cache.containsKey(pair)){
            return cache.get(pair);
        }
        // 如果模式串已经没有了
        if (patternIndex >= pattern.length) {
            // 如果主串也没有了，则表示OK； 如果主串还有，则表示比对失败
            return mainIndex >= main.length;
        }
        // 获取表达式
        Expression exp = getNextExpression(pattern,patternIndex);

        // 单表达式,不包括*号
        if (!exp.isAny){
            boolean res = exp.match(main, mainIndex) && isMatch(main, mainIndex+1, pattern, patternIndex+1, cache);
            cache.put(pair, res);
            return res;
        }
        // 包括*号
        boolean res = false;
        for (int i = mainIndex; i <= main.length; i++){
            // 此轮比对, [i,)的主串与后续的模式串是否比对OK
            res = isMatch(main, i, pattern, patternIndex+2, cache);
            // 如果比对OK，则表明是OK的，可以直接返回; 如果当前字符串与模式串不能匹配, 则表示不能进行比对; 返回最后的结果就好
            if (res || !exp.match(main,i)){
                break;
            }
        }
        //缓存结果
        cache.put(pair, res);
        return res;
    }

    private static class Pair {
        private final int mainOffset;
        private final int patternOffset;

        public Pair(int mainOffset, int patternOffset) {
            this.mainOffset = mainOffset;
            this.patternOffset = patternOffset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return mainOffset == pair.mainOffset &&
                    patternOffset == pair.patternOffset;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mainOffset, patternOffset);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(!s.isMatch2("aa","a"));
//        System.out.println(s.isMatch2("aa","a*"));
//        System.out.println(s.isMatch2("abc","abc*"));
//        System.out.println(s.isMatch2("abc","a*b*c*"));
//        System.out.println(s.isMatch2("abc","abcd*"));
//        System.out.println(s.isMatch2("aab","c*a*b"));
//        System.out.println(!s.isMatch2("mississippi","mis*is*p*."));
//        System.out.println(s.isMatch2("",""));
        System.out.println(s.isMatch2("",".*"));
        System.out.println(s.isMatch2("","a*"));

    }


    private boolean isMatch2(String mainStr, String patternStr){
        char[] main = mainStr.toCharArray();
        char[] pattern = patternStr.toCharArray();

        List<Expression> expressionList = new ArrayList<>();
        int patternIndex = 0;
        while(patternIndex < pattern.length){
            Expression exp = getNextExpression(pattern, patternIndex);
            expressionList.add(exp);
            patternIndex += exp.isAny ? 2 : 1;
        }
        boolean[][] dp = new boolean[main.length+1][expressionList.size()+1];
        dp[0][0] = true;
        for (int expIndex = 1; expIndex <= expressionList.size(); expIndex++){
            Expression exp = expressionList.get(expIndex-1);
            for (int mainIndex = 1; mainIndex <= main.length; mainIndex++){
                // 将该表达式下的上一个元素Copy到现有的属性中
                dp[mainIndex-1][expIndex] = dp[mainIndex-1][expIndex] || (exp.isAny && (dp[mainIndex-1][expIndex-1]));
                // 计算该值
                dp[mainIndex][expIndex] = dp[mainIndex-1][expIndex-1] && exp.match(main, mainIndex-1);
                // 尝试向上走
                dp[mainIndex][expIndex] = dp[mainIndex][expIndex] || (exp.isAny && dp[mainIndex-1][expIndex] && exp.match(main, mainIndex-1));
                // 将表达式下，当前元素的属性copy
                dp[mainIndex][expIndex] = dp[mainIndex][expIndex] || (exp.isAny && (dp[mainIndex][expIndex-1]));
            }
        }
        return dp[main.length][expressionList.size()];
    }

    private Expression getNextExpression(char[] pattern, int patternIndex) {
        char exp = pattern[patternIndex];
        boolean isAny = patternIndex+1 < pattern.length && pattern[patternIndex+1] == '*';
        return new Expression(exp,isAny);
    }

    private static class Expression {
        private final char expression;
        private final boolean isAny;

        public Expression(char expression, boolean isAny) {
            this.expression = expression;
            this.isAny = isAny;
        }

        private boolean match(char[] main, int mainIndex) {
            // 如果为空
            if (mainIndex == main.length) return isAny;
            // 如果有,则比对是都OK
            return expression == '.' || expression == main[mainIndex];
        }
    }


}
