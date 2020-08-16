package com.sfmd.algorithm.leetCode.s22;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Solution {

    public List<String> generateParenthesis(int n) {
        Set<String> set = new HashSet<>();
        generate(0,0,n,"",set);
        return new ArrayList<>(set);
    }

    private void generate(int left, int right, int n, String s,Set<String> result){
        if(left == n && right == n){
            result.add(s);
        }
        if(left < n){
            generate(left+1, right, n, s+"(", result);
        }
        if(right < left){
            generate(left, right+1, n, s + ")", result);
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().generateParenthesis(3)));

        int[] array = new int[]{1,2,3};
        List<Integer> seed = new ArrayList<>();
        for (int value : array) {
            seed.add(value);
        }
    }

}
