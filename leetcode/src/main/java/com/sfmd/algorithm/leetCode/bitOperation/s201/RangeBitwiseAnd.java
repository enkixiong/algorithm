package com.sfmd.algorithm.leetCode.bitOperation.s201;

public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int m, int n) {
        int andResult = m & n;
        if (andResult == 0){
            return andResult;
        }
        int i = 0;
        while (m != n || n != andResult) {
            m = m >> 1;
            n = n >> 1;
            andResult = andResult >> 1;
            i++;
        }
        return andResult << i;
    }

    public static void main(String[] args) {
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(5,7) == 4);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(0,1) == 0);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(8, 15) == 8);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(16, 31) == 16);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(0, 1) == 0);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(1, 3) == 0);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(3, 3) == 3);
        System.out.println(new RangeBitwiseAnd().rangeBitwiseAnd(5,5) == 5);

    }

}
