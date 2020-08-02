package com.sfmd.algorithm.leetCode.s179;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        String[] array = new String[nums.length];
        int length = 0;
        boolean isAllZero = true;
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
            length = Math.max(length, array[i].length());
            if (nums[i] != 0){
                isAllZero = false;
            }
        }
        if (isAllZero){
            return String.valueOf(0);
        }
        InnerInfo[] infoArray = new InnerInfo[nums.length];
        for (int i = 0; i < array.length; i++) {
            char[] charArray = array[i].toCharArray();
            char[] filledCharArray = new char[length];
            System.arraycopy(charArray, 0, filledCharArray, 0, charArray.length);
            Arrays.fill(filledCharArray, charArray.length, length, (char) Math.max(charArray[charArray.length - 1],charArray[0]));
            infoArray[i] = new InnerInfo(filledCharArray,array[i]);
        }
        Arrays.sort(infoArray);
        StringBuilder builder = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            builder.append(infoArray[i].str);
        }
        return builder.toString();
    }

    private static class InnerInfo implements Comparable<InnerInfo> {
        private final char[] filledChar;
        private final String str;

        private InnerInfo(char[] filledChar, String str) {
            this.filledChar = filledChar;
            this.str = str;
        }

        @Override
        public int compareTo(InnerInfo o) {
            int result =  new String(filledChar).compareTo(new String(o.filledChar));
            if (result == 0){
                result = str.length() - o.str.length() ;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestNumber(new int[]{10, 2}).equals("210"));
        System.out.println(new Solution().largestNumber(new int[]{3, 30, 34, 5, 9}).equals("9534330"));
        System.out.println(new Solution().largestNumber(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247}).equals("9609938824824769735703560743981399"));
        System.out.println(new Solution().largestNumber(new int[]{12,121}).equals("12121"));
        System.out.println(new Solution().largestNumber(new int[]{9051,5526,2264,5041,1630,5906,6787,8382,4662,4532,6804,4710,4542,2116,7219,8701,8308,957,8775,4822,396,8995,8597,2304,8902,830,8591,5828,9642,7100,3976,5565,5490,1613,5731,8052,8985,2623,6325,3723,5224,8274,4787,6310,3393,78,3288,7584,7440,5752,351,4555,7265,9959,3866,9854,2709,5817,7272,43,1014,7527,3946,4289,1272,5213,710,1603,2436,8823,5228,2581,771,3700,2109,5638,3402,3910,871,5441,6861,9556,1089,4088,2788,9632,6822,6145,5137,236,683,2869,9525,8161,8374,2439,6028,7813,6406,7519}).equals("995998549642963295795569525905189958985890288238775871870185978591838283748308830827481618052787813771758475277519744072727265721971071006861683682268046787640663256310614560285906582858175752573156385565552654905441522852245213513750414822478747104662455545424532434289408839763963946391038663723370035134023393328828692788270926232581243924362362304226421162109163016131603127210891014"));


//        "995998549642963295795569525905189958985890288238775871870185978591838283748308308827481618052787813771758475277519744072727265721971071006861683682268046787640663256310614560285906582858175752573156385565552654905441522852245213513750414822478747104662455545424532434289408839763963946391038663723370035134023393328828692788270926232581243924362362304226421162109163016131603127210891014"
//        "995998549642963295795569525905189958985890288238775871870185978591838283748308830827481618052787813771758475277519744072727265721971071006861683682268046787640663256310614560285906582858175752573156385565552654905441522852245213513750414822478747104662455545424532434289408839763963946391038663723370035134023393328828692788270926232581243924362362304226421162109163016131603127210891014"

//        [9051,5526,2264,5041,1630,5906,6787,8382,4662,4532,6804,4710,4542,2116,7219,8701,8308,957,8775,4822,396,8995,8597,2304,8902,830,8591,5828,9642,7100,3976,5565,5490,1613,5731,8052,8985,2623,6325,3723,5224,8274,4787,6310,3393,78,3288,7584,7440,5752,351,4555,7265,9959,3866,9854,2709,5817,7272,43,1014,7527,3946,4289,1272,5213,710,1603,2436,8823,5228,2581,771,3700,2109,5638,3402,3910,871,5441,6861,9556,1089,4088,2788,9632,6822,6145,5137,236,683,2869,9525,8161,8374,2439,6028,7813,6406,7519]
    }
}
