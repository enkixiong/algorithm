package com.sfmd.algorithm.leetCode.bitOperation.s136_singleNumber;

public class SingleNumber3 {

    public int[] singleNumber(int[] nums) {

        int mask = 0;
        for (int num : nums){
            mask ^= num;
        }
        // 此时: mask 是 X^Y的结果, 因为X和Y只出现了一次，所以一定会有差异项
        // 以下操作可以获取: 1) 最低位为1的数字 : 0b0010这种,高位全部为0;
        int diffLowestFlag = mask & (-mask);

        int z = 0;
        // 通过这个标记位，可以过滤该类型数值不参与异或运算
        // (也就是以该位为标志位,将数组拆分为2部分，一部分该检测位为1，一部分该检测位为0)
        for (int num : nums){
            if ((diffLowestFlag & num) == 0){
                z ^= num;
            }
        }
        // 最终的z，其实一定是{X,Y}中的一个元素
        return new int[]{z,mask^z};
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber3().singleNumber(new int[]{1,2,1,3,2,5})[0]);
    }

}
