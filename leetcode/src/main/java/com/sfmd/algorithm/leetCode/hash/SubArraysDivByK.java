package com.sfmd.algorithm.leetCode.hash;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class SubArraysDivByK {

    public int subarraysDivByK(int[] a, int k) {
        Map<Integer, Integer> kMap = new HashMap<>(k);
        kMap.put(0,1);
        int sum = 0;
        for (int value : a) {
            sum += value;
            int mod = (sum % k + k) % k;
            int count = kMap.getOrDefault(mod, 0);
            kMap.put(mod, count + 1);
        }

        int result = 0;
        for (int i : kMap.keySet()){
            int n = kMap.get(i);
            result += n*(n-1)/2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubArraysDivByK().subarraysDivByK(new int[]{4,5,0,-2,-3,1},5));
        System.out.println(new SubArraysDivByK().subarraysDivByK(new int[]{7,-4,10},5));
        System.out.println(new SubArraysDivByK().subarraysDivByK(new int[]{10,-4,-1,7,3,5},5));
        System.out.println(new SubArraysDivByK().subarraysDivByK(new int[]{10,7,-4},5));
    }

}
