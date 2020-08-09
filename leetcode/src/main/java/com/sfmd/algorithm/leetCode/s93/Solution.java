package com.sfmd.algorithm.leetCode.s93;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<String> restoreIpAddresses(String s) {
        char[] array = s.toCharArray();
        char[] generate = new char[array.length + 5];
        List<String> result = new LinkedList<>();
        dfs(array, generate, 0, 1,  result);
        return result;
    }

    private void dfs(char[] main, char[] generate, int scan, int offset, List<String> result){
        System.out.println(String.format("%d -> %d", scan, offset));
        if(offset > 4 ){
            if (scan == main.length) {
                result.add(new String(generate, 0, main.length + 3));
            }
            return;
        }

        if(main[scan] == '0'){
            generate[offset-1+scan] = main[scan];
            generate[offset+scan] = '.';
            dfs(main,generate, scan+1, offset+1, result);
            return;
        }

        int current = 0;
        for(int i = 0; i < 3; i++){
            if(scan +i >= main.length){
                break;
            }
            current = current*10+ (main[scan+i] - '0');
            if(current > 255){
                break;
            }
            generate[offset-1+scan+i] = main[scan+i];
            generate[offset+scan+i] = '.';
            dfs(main,generate, scan+i+1, offset+1, result);
        }
    }

    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(new Solution().restoreIpAddresses("25525511135")));
        System.out.println(JSON.toJSONString(new Solution().restoreIpAddresses("0000")));

        ;

    }

}
