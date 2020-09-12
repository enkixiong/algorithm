package week_06.s403;

import java.util.*;

/**
 * 朴素的自顶向上DFS的解法, 没有进行提前剪枝
 */
class PureDFSSolution {

    public boolean canCross(int[] stones) {

        // 上一步可以跳跃多大的位置, 也就是可以落几个石子

        // 落在某个石子上,当前可以跳跃的步骤是: [k-1,k+1];

        // 如果用自顶向下呢?

        // 17, 如果跳到17这个位置,就是说,跳跃至 12, 并且跳跃步骤是5, 也就是说, 12往前跳的步伐是: [4,6]

        // 也就是有两个变量,当前位置, 以及当前步骤可以跳跃的区间;

        // [0,1,1] --> 编号为0的,可以跳跃的区间是: [1,1]
        // [1,1,2] --> 编号为1的, 可以跳跃的区间是: [1,2], 也就是说，可以达到编号为2,3这两个石子
        // 这里的方式是: 自底向上,但是并不利于提前剪枝,实际上是一个朴素的 BFS

        Map<Integer, Set<Integer>> dp = new HashMap<>();

        for(int i = 0; i < stones.length; i++){
            dp.put(stones[i], new HashSet<>());
        }

        dp.get(stones[0]).add(1);

        for(int i = 0; i < stones.length; i++){
            Set<Integer> canSteps = dp.get(stones[i]);
            System.out.println(stones[i]+"->"+ canSteps.toString());
            for(int step : canSteps){
                if(step <= 0){
                    continue;
                }
                int nextLocation = stones[i] + step;
                if(!dp.containsKey(nextLocation)){
                    continue;
                }
                Set<Integer> nextSteps = dp.get(nextLocation);

                nextSteps.add(step-1);
                nextSteps.add(step);
                nextSteps.add(step+1);
            }
        }
        return !dp.get(stones[stones.length-1]).isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(new DPSolution().canCross(new int[]{0,1,3,5,6,8,12,17}));
    }

}
