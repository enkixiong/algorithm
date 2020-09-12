package week_06.s77;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class DFSSolution {

    public List<List<Integer>> combine(int n, int k) {
        if (k == 0 || k > n) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new LinkedList<>();
        dfs(n, k, 1, new ArrayList<>(k), result);

        return result;
    }

    private void dfs(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {

        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // end = n-1 是朴素的做法,没有考虑剪枝的情况
        int end = n - k +1; // 剪枝的情况,提前将不可能的情况排除在外

        // 开始DFS
        for(int i = start; i <= end; i++){
            // 处理本层
            current.add(i);
            // drill down
            dfs(n, k-1, i+1, current, result);
            // 清除本层的状态
            current.remove(current.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new DFSSolution().combine(13,13)));
    }

}
