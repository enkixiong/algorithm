package com.sfmd.algorithm.leetCode.s632;

import com.alibaba.fastjson.JSON;

import java.util.*;

class Solution {


    public int[] smallestRange(List<List<Integer>> nums) {
        if(nums.size() == 1){
            return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
        }
        // 双指针(滑动窗口),尝试囊括不同的元素
        // 1. 找到初始指针的左区间/右区间, 来包含最小的元素，
        // 2.尝试将区间缩小,一直到区间不能再缩小为止
        // 3. 更新结果集
        // 4. 去除一个元素,使滑动窗口不满足条件;  ?? 如何快速检测,滑动窗口内是否包含了所有数组的元素？
        //    4.1 如果该元素为该数组最大的元素,则直接退出
        // 5. 窗口不断向右探寻,使其满足条件, 跳转至2

        // 记录区间内元素的信息，严格单调递增, 有必要吗？有，否则不知道下一个元素在哪；
        // 记录当前元素下一个需要扫描的位置
        int[] memo = new int[nums.size()];
        // 初始化滑动窗口
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // 1. 找到初始指针的左区间/右区间, 来包含最小的元素
        for (int i = 0; i < nums.size(); i++) {
            min = Math.min(nums.get(i).get(0),min);
            max = Math.max(nums.get(i).get(0), max);
        }
        List<Pair> init = getWaitingAppendItems(nums, memo, max);
        Deque<Pair> deque = new ArrayDeque<Pair>(init);
        int currentMax = max;

        // 开始更新区间并且构造下一个可用的区间
        while(true){
            Pair pair = deque.removeFirst();
            // 队列中还含有该元素
            // 2.尝试将区间缩小,一直到区间不能再缩小为止
            if (pair.j+1 != memo[pair.i]){
                continue;
            }
            // 此时,说明滑动窗口是当前最小的,尝试更新;
            // 3. 更新结果集
            int currentMin = nums.get(pair.i).get(pair.j);
            if (currentMax-currentMin < max - min){
                max = currentMax;
                min = currentMin;
            }
            // 4. 去除一个元素,使滑动窗口不满足条件;
            // 删除了该元素后,不会再有新的元素,则直接退出
            //    4.1 如果该元素为该数组最大的元素,则直接退出
            if (pair.j+1 == nums.get(pair.i).size()){
                break;
            }
            // 下一个待加入的值
            //  5. 窗口不断向右探寻,使其满足条件, 跳转至2
            currentMax = nums.get(pair.i).get(memo[pair.i]);
            // 将 <= 该元素的值, 全部加入队列中
            List<Pair> nextInclude = getWaitingAppendItems(nums,memo,currentMax);
            deque.addAll(nextInclude);
        }
        return new int[]{min,max};
    }

    private List<Pair> getWaitingAppendItems(List<List<Integer>> nums, int[] memo, int max) {
        List<Pair> init = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = memo[i]; j < nums.get(i).size(); j++) {
                if (nums.get(i).get(j) <= max){
                    memo[i] = j+1;
                    init.add(new Pair(i,j));
                }else{
                    break;
                }
            }
        }
        init.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return nums.get(o1.i).get(o1.j) - nums.get(o2.i).get(o2.j);
            }
        });
        return init;
    }

    private static class Pair{
        private final int i;
        private final int j;

        private Pair(int i, int j){
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> info = new ArrayList<>();
        info.add(Arrays.asList(4,10,15,24,26));
        info.add(Arrays.asList(0,1,10));
        info.add(Arrays.asList(5,10,18,22,30));
        System.out.println(JSON.toJSONString(new Solution().smallestRange(info)));
    }

}
