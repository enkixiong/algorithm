package com.sfmd.algorithm.leetCode.string;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class StringFindAlgorithm {

    public int bm(String mainStr, String pattern){

        char[] mainStrArray = mainStr.toCharArray();
        char[] patternArray = pattern.toCharArray();

        // TODO 坏字符规则: 1) 构造hash表
        Map<Character,Integer> hash = new HashMap<>();
        for (int i = 0; i < patternArray.length; i++){
            hash.put(patternArray[i],i);
        }

        // TODO 初始化滑动窗口,滑动窗口起始位置:0,终止位置为: mainStrArray.length - patternArray.length -1
        int beginIndex = 0;
        int endIndex = mainStrArray.length - patternArray.length ;
        while(beginIndex <= endIndex){

            int si = -1;
            // 1. 查找坏字符
            for (int i = patternArray.length-1; i >= 0; i--){
                if (patternArray[i] != mainStrArray[beginIndex+i]){
                    si = i;
                    break;
                }
            }
            if (si == -1){
                return beginIndex;
            }

            // 2. 查找坏字符规则能滑动的距离
            int wrongCharSliderOffset = si - hash.getOrDefault(mainStrArray[beginIndex+si],-1);
            // 3. 查找好后缀规则能滑动的距离
            int goodSuffixSliderOffset = getGoodSuffixSliderOffset(mainStrArray, patternArray, si, beginIndex);
            // 4. 滑动
            beginIndex += Math.max(wrongCharSliderOffset, goodSuffixSliderOffset);
        }

        return -1;

    }

    private int getGoodSuffixSliderOffset(char[] mainStrArray, char[] pattern, int wrongCharIndex, int beginIndex){

        // 初始化好后缀的开始&结束
        int[] suffix = new int[pattern.length - wrongCharIndex];
        boolean[] prefix = new boolean[suffix.length];
        generateGS(pattern, pattern.length, suffix, prefix);
        return -1;
    }


    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    public int bm(char[] a, char[] b) {
        int[] bc = new int[256]; // 记录模式串中每个字符最后出现的位置
        for (int i = 0; i < b.length; i++){
            bc[b[i]] = i;
        }
        int n = a.length;
        int m = b.length;
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        System.out.println(JSON.toJSONString(suffix));
        System.out.println(JSON.toJSONString(prefix));
//        System.out.println(moveByGS(5,m,suffix,prefix));
//        System.out.println(moveByGS(4,m,suffix,prefix));
//        System.out.println(moveByGS(3,m,suffix,prefix));
//        System.out.println(moveByGS(2,m,suffix,prefix));
//        System.out.println(moveByGS(1,m,suffix,prefix));
//        System.out.println(moveByGS(0,m,suffix,prefix));
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];//坏字符规则移动的下标
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);// 好后缀移动的下标
            }
            System.out.println(String.format("当前下标:%d 坏字符移动位置:%d 好前缀移动位置:%d 下一个下标:%d", i, x, y , i + Math.max(x, y)));
            i = i + Math.max(x, y); // 向后移动
        }
        return -1;
    }

    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j+1 - suffix[k] ; // j+1 逻辑下标:第多少位元素;
        // 为什么j+2?
        for (int r = j+2; r <= m-1; ++r) {
            if (prefix[m-r]) {
                return r;
            }
        }

//        for (int r = k; r >=1; --r) {
//            if (prefix[r]) {
//                return r+1;
//            }
//        }
        return m;
    }


    // b表示模式串，m表示长度，suffix，prefix数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]) { // 与b[0, m-1]求公共后缀子串
                suffix[++k] = --j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
        }
    }


    public static void main(String[] args) {
        System.out.println(new StringFindAlgorithm().bm("eecafeecafecaf".toCharArray(),"fecaf".toCharArray()));
    }

}
