package com.sfmd.algorithm.leetCode.s187;

import java.util.*;

class Solution {

    private static final long LIMIT = 1000000000L;
    int[] hashMap = new int[26];

    public List<String> findRepeatedDnaSequences(String s) {

        if(s.length() <= 10){
            return Collections.emptyList();
        }
        hashMap[0] = 1;
        hashMap['C'-'A'] = 2;
        hashMap['G'-'A'] = 3;
        hashMap['T'-'A'] = 4;

        // 滚动hash

        // 1. 起始位置为0，开始计算10位的hash值

        // 2. 将扫描到的hash值存储起来

        // 3. 如果hashMap中存储了该hash,并且无重叠位置, 则说明存在重复的子串

        Map<Long,Integer> hashed = new HashMap<>();
        Set<Long> alreadyFind = new HashSet<>();

        char[] main = s.toCharArray();
        long hash = 0;
        for (int i = 0; i < 9; i++){
            hash = hash * 10 + getIntValue(main[i]);
        }

        List<String> result = new ArrayList<>();
        // 从10位开始，加入hash
        for (int i = 9; i < main.length; i++) {
            hash = hash(hash, main[i]);
            int beginIndex = hashed.getOrDefault(hash,-1);
            // 没有重复的元素
            int currentBeginIndex = i -9;
            if (beginIndex == -1){
                hashed.put(hash, currentBeginIndex);
            }else{
                // 虽然重复，但是有相交的区间
                if (alreadyFind.contains(hash)){
                    continue;
                }
                result.add(new String(main,currentBeginIndex,10));
                alreadyFind.add(hash);
            }
        }

        return result;
    }

    private long hash(long hash, char newVal){
        return (hash % LIMIT) * 10 + getIntValue(newVal);
    }

    private int getIntValue(char c){
        return hashMap[c - 'A'];
    }


    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAAAAAAAAA"));
    }

}
