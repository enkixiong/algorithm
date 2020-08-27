package week_04.s433;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int minMutation(String start, String end, String[] bank) {

        // 题型: 最短路径,单源最短路径的题

        // 1. 最短路径生成算法
        List<Integer> candidate = new ArrayList<>();
        Map<Integer,List<Integer>> cache = new HashMap<>();
        int target = -1;
        for(int i = 0; i < bank.length; i++){
            cache.put(i, new ArrayList<>());
            if(canConvert(start, bank[i])){
                candidate.add(i);
            }
            if(end.equals(bank[i])){
                target = i;
            }
        }

        // 找到target的下标
        if(target == -1){
            return -1;
        }

        for (int i = 0; i < bank.length; i++){
            for (int j = i+1; j < bank.length; j++){
                if(canConvert(bank[i], bank[j])){
                    cache.get(i).add(j);
                    cache.get(j).add(i);
                }
            }
        }

        int[] visited = new int[bank.length];
        return bfs(visited,candidate, cache, target);
    }

    private boolean canConvert(String a, String b){
        int notEqualSize = 0;
        for (int i = a.length() - 1; i >= 0 ; i--){
            if (a.charAt(i) != b.charAt(i)){
                notEqualSize++;
            }
            if(notEqualSize > 1){
                return false;
            }
        }
        return notEqualSize == 1;
    }

    private int bfs(int[] visited, List<Integer> candidate, Map<Integer,List<Integer>> cache, int target){
        int steps = 1;
        while(true){
            if(candidate.contains(target)){
                return steps;
            }
            if (candidate.size() == 0){
                return -1;
            }
            List<Integer> nextLevel = new ArrayList<>();
            for (int candi : candidate) {
                for(int offset : cache.get(candi)){
                    if(visited[offset] == 1){
                        continue;
                    }
                    visited[offset] = 1;
                    nextLevel.add(offset);
                }
            }
            candidate = nextLevel;
            steps++;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().minMutation("AACCGGTT","AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(new Solution().minMutation("AAAAACCC","AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
//        "AAAAACCC"
//        "AACCCCCC"
//                ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
    }
}
