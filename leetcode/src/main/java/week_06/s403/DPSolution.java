package week_06.s403;

import java.util.*;

class DPSolution {

    // 在朴素的自底向上DFS的情况中, 没有对当前情况进行最终解决,
    // 而是将当前情况的可能性由当前可以跳跃到的石头解决
    // 这里是简单的分治&DFS,性能不太高

    // 如果在某个阶段，就可以提前判定，当前选择是可以的，或者不可以的，
    // 那是不是可以实现提前剪枝&提前返回结果？

    // 本题的状态: 当前石头编号,下一步跳跃的步骤,
    // 结果: 能否跳跃至最终的节点,并且该记录是不需要记的，因为我们只需要知道能否跳跃就OK了;


    // 记录的是,当前节点的某个跳跃步骤下,不能够跳跃至最终节点

    private int target;

    public boolean canCross(int[] stones) {
        if (stones.length == 1) {
            return true;
        }
        if (stones[1] != 1) {
            return false;
        }

        Map<Integer, Set<Integer>> memo = new HashMap<>();
        for (int stone : stones) {
            memo.put(stone, new HashSet<>());
        }

        target = stones[stones.length - 1];

        return dfs(0, 1, memo);
    }

    private boolean dfs(int offset, int step, Map<Integer, Set<Integer>> memo) {

        if (offset == target) {
            return true;
        }
        if (step <= 0) {
            return false;
        }

        int next = offset + step;

        Set<Integer> jumpFailed = memo.get(next);

        if (jumpFailed == null || jumpFailed.contains(step)) {
            return false;
        }

        boolean result = dfs(next, step + 1, memo)
                || dfs(next, step, memo)
                || dfs(next, step - 1, memo);
        jumpFailed.add(step);
        return result;
    }

    public static void main(String[] args) {
    }

}
