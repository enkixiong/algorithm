package com.sfmd.algorithm.leetCode.s1431;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max = 0;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        List<Boolean> resultList = new ArrayList<>(candies.length);
        int cardinality = max - extraCandies;
        for (int candy : candies) {
            resultList.add(candy >= cardinality);
        }

        return resultList;
    }

}
