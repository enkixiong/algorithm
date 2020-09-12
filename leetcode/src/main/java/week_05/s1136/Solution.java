package week_05.s1136;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Solution {

    public int minimumSemesters(int n, int[][] relations) {

        // 所有的课程, 都必须要等0这门课程先修完
        int[] inMap = new int[n+1];
        Map<Integer, List<Integer>> outMap = new HashMap<>();
        // 将relations编织为图的入度和出度 入度表示: 必须要先这门课实现的先修课完成
        for(int[] relation : relations){
            // 课程Y的入度 +1
            inMap[relation[1]] += 1;
            // 增加课程X的出度
            if(!outMap.containsKey(relation[0])){
                outMap.put(relation[0], new LinkedList<>());
            }
            outMap.get(relation[0]).add(relation[1]);
        }

        Deque<Integer> zeroList = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(inMap[i] == 0){
                zeroList.add(i);
            }
        }

        int level = 0;
        // 将当前节点全部编为1,并且,
        while(!zeroList.isEmpty()){
            level++;
            int levelSize = zeroList.size();
            for(int i = 0; i < levelSize; i++){
                int zeroIn = zeroList.removeFirst();
                if(!outMap.containsKey(zeroIn)){
                    continue;
                }
                // 删除第一个元素
                for(Integer next : outMap.get(zeroIn)){
                    inMap[next]--;
                    if(inMap[next] == 0){
                        zeroList.addLast(next);
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            if(inMap[i] != 0){
                return -1;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumSemesters(3, new int[][]{{1,3},{2,3}}));
        System.out.println(new Solution().minimumSemesters(3, new int[][]{{1,2},{2,3},{3,1}}));
    }

}
