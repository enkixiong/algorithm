package com.sfmd.algorithm.leetCode.s30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        if (words.length == 0){
            return result;
        }

        Map<String,Integer> wordsCountInfo = new HashMap<>();
        for (String word : words) {
            wordsCountInfo.put(word,wordsCountInfo.getOrDefault(word,0)+1);
        }

        int wordLength = words[0].length();
        int windowLength = wordLength*words.length;
        for (int i = 0; i < wordLength; i++) {
            int right = windowLength+i;
            while (right <= s.length()) {
                boolean matched = true;
                Map<String, Integer> currentMap = new HashMap<>();
                for (int j = 1; j <= words.length; j++) {
                    String currentWord = s.substring(right - j * wordLength, right - (j - 1) * wordLength);
                    currentMap.put(currentWord, currentMap.getOrDefault(currentWord, 0) + 1);
                    if (currentMap.get(currentWord) > wordsCountInfo.getOrDefault(currentWord, 0)) {
                        // 出现了坏字符
                        right += (words.length - j + 1) * wordLength;
                        matched = false;
                        break;
                    }
                }
                if (matched) {
                    result.add(right - windowLength);
                    right += wordLength;
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
        System.out.println(new Solution().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        System.out.println(new Solution().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
    }

}
