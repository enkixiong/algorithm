package com.sfmd.algorithm.leetCode.bitOperation.s136_singleNumber;

public class SingleNumber2 {

    // 原理: 该数字,所有的处理与位处理的逻辑一致;
    // 当数字为 0b10时;
    // 第一次出现:
    //      once = ~appearTwice & (appearOnce ^ num) = ~0 & (0 ^ 1) = 1;
    //     twice = ~appearOnce & (appearTwice ^ num) = ~1 & (0 ^ 1) = 0;
    // 第二次出现:
    //      once = ~appearTwice & (appearOnce ^ num) = ~0 & (1 ^ 1) = 0;
    //     twice = ~appearOnce & (appearTwice ^ num) = ~0 & (0 ^ 1) = 1;
    // 第三次出现:
    //      once = ~appearTwice & (appearOnce ^ num) = ~1 & (1 ^ 1) = 0;
    //     twice = ~appearOnce & (appearTwice ^ num) = ~0 & (1 ^ 1) = 0;
    public int singleNumber(int[] nums) {
        int appearOnce = 0;
        int appearTwice = 0;

        for (int num :  nums){
            // 出现一次，但是并不在第二次的数据中
            appearOnce = ~appearTwice & (appearOnce ^ num);
            // 如果该行是第二次出现，那么appearOnce位为0(连续两次^, 也就是 0^num^num = 0)
            appearTwice = ~appearOnce & (appearTwice ^ num);
        }

        return appearOnce;
    }

    public static void main(String[] args) {
        int b = 0b00010001;
        System.out.println(Integer.toBinaryString(~b));

        int a = 5;
        System.out.println(a&3);

        int c = 5;
        System.out.println(b^b);
        System.out.println(b^0);

        System.out.println(0^0);
        System.out.println(1^1);

        System.out.println(Integer.toBinaryString(-1));

        int mask = 0b0011^0b0101;
        int diff = mask & (-mask);
        System.out.println(Integer.toBinaryString(mask));
        System.out.println(Integer.toBinaryString(diff));
        System.out.println(String.format("%d:%s", diff,Integer.toBinaryString(diff)));
        System.out.println(3 & diff);
        System.out.println(5 & diff);


    }

}
