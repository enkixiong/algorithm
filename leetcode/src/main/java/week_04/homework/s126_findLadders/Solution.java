package week_04.homework.s126_findLadders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        int target = -1;
        // 边界条件检查
        for(int i = 0; i < wordList.size(); i++){
            String word = wordList.get(i);
            if(word.equals(endWord)){
                target = i;
                break;
            }
        }
        if(target == -1){
            return Collections.emptyList();
        }

        wordList.add(beginWord);
        // 1. 构造顶点
        int[] dp = new int[wordList.size()];
        Arrays.fill(dp,-1);

        int[][] paths = new int[dp.length][dp.length];
        // 2. 构造图的边
        for(int i = 0; i < wordList.size(); i++){
            for(int j = i+1; j < wordList.size(); j++){
                if(canTransfer(wordList.get(i), wordList.get(j))){
                    paths[i][j] = 1;
                    paths[j][i] = 1;
                }
            }
        }

        // 3. 层次遍历; 找到目标地点
        List<Integer> level = Collections.singletonList(dp.length-1);
        dp[dp.length-1] = 0;
        bfs(level,1,paths,dp,target);

        // 无法到达节点
        if(dp[target] == -1){
            return Collections.emptyList();
        }
        // 4.回溯,最终最优解
        return backTrace(target, dp,paths, wordList);

        // 5. 返回结果


    }

    // 采取的思路: 拓扑排序,像水波一样,通过BFS向前扩散
    private void bfs(List<Integer> level, int depth, int[][] paths, int[] dp, int target) {

        // terminator
        if(level.isEmpty()){
            return;
        }

        // 处理当前层
        List<Integer> nextLevel = new ArrayList<>();
        for(int l : level) {
            for (int i = 0; i < dp.length; i++) {
                // 连通 并且 没有被访问到
                if(paths[l][i] == 1 && dp[i] == -1){
                    nextLevel.add(i);
                    dp[i] = depth;
                    if(i == target){
                        return;
                    }
                }
            }
        }

        // drill down

        bfs(nextLevel, depth+1, paths, dp, target);

        // clear current level status
    }

    // 根据DP的结果进行回溯,思想来源: 王争的 《数据结构与算法之美》
    private List<List<String>> backTrace(int target, int[] dp, int[][] paths, List<String> wordList){
        List<List<Integer>> level = new ArrayList<>();
        level.add(Collections.singletonList(target));
        level = doBackTrace(level, dp[target], dp,paths);
        return reverseAndTranslate(level, wordList);
    }

    // 如果当前值为i, 那么在DP中找到i-1并且这两个点之间是相连的,表明这是结果集中的数据
    private List<List<Integer>> doBackTrace(List<List<Integer>> level, int target, int[] dp, int[][] paths) {
        List<List<Integer>> nextLevel = new ArrayList<>();
        int nextTarget = target-1;
        for(List<Integer> list : level){
            int endResult = list.get(list.size()-1);
            for(int i = 0; i < dp.length; i++){
                if(paths[endResult][i] == 1 && dp[i] == nextTarget){
                    List<Integer> path = new ArrayList<>(list);
                    path.add(i);
                    nextLevel.add(path);
                }
            }
        }
        return nextTarget == 0 ? nextLevel : doBackTrace(nextLevel,nextTarget,dp,paths);
    }

    // 因为数据是从终点开始找,数组是逆序的,所以需要倒叙
    private List<List<String>> reverseAndTranslate(List<List<Integer>> level, List<String> wordList) {
        List<List<String>> result = new ArrayList<>(level.size());
        for(List<Integer> path : level){
            List<String> transferPath = new ArrayList<>(path.size());
            for(int i = path.size()-1; i >= 0; i--){
                transferPath.add(wordList.get(path.get(i)));
            }
            result.add(transferPath);
        }
        return result;
    }

    // 比较两个字符串之间是否可以相连
    private boolean canTransfer(String a, String b){
        // 差异项
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count == 1;
    }

}
