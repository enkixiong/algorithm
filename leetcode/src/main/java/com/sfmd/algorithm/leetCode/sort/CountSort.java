package com.sfmd.algorithm.leetCode.sort;

/**
 * 计数排序
 */
public class CountSort {

    /**
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
        int reminder = 0;
        int[] countArray = new int[26];
        for (char c : s.toCharArray()){
            int offset = c - 0b01100001;
            int current = countArray[offset];
            if (current == 0){
                reminder++;
            }
            countArray[offset] = current+1;
        }
        char[] newCharArray = new char[s.length()];
        int newOffset = 0;
        while(reminder > 0){
            // 从小到大
            for (int i = 0; i < countArray.length; i++){
                if (countArray[i] == 0){
                    continue;
                }
                char charValue = (char) (i+0b01100001);
                int currentCount = countArray[i] -1;
                countArray[i] = currentCount;
                if (currentCount == 0){
                    reminder--;
                }
                newCharArray[newOffset] = charValue;
                newOffset++;
            }
            // 从大到小
            for (int i = countArray.length -1; i >= 0; i--){
                if (countArray[i] == 0){
                    continue;
                }
                char charValue = (char) (i+0b01100001);
                int currentCount = countArray[i] -1;
                countArray[i] = currentCount;
                if (currentCount == 0){
                    reminder--;
                }
                newCharArray[newOffset] = charValue;
                newOffset++;
            }
        }
        return new String(newCharArray);
    }

    public static void main(String[] args) {
        System.out.println(new CountSort().sortString("aaaabbbbcccc").equals("abccbaabccba"));
        System.out.println(new CountSort().sortString("rat").equals("art"));
        System.out.println(new CountSort().sortString("leetcode").equals("cdelotee"));
        System.out.println(new CountSort().sortString("ggggggg").equals("ggggggg"));
        System.out.println(new CountSort().sortString("spo").equals("ops"));
        System.out.println(new CountSort().sortString("").equals(""));
    }

}
