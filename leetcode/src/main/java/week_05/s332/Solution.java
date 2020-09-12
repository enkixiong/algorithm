package week_05.s332;

import javafx.scene.layout.Priority;

import java.util.*;

class Solution {

    private int length = 0;

    public List<String> findItinerary(List<List<String>> tickets) {

        // 这一题是什么样的题型? 在一个规定好的路径里面,将所有的路径都遍历一遍; 数据结构是带环的有向图

        // 1. 指定起点: 也就是root节点

        // 2. 开始DFS: 选择邻接节点;

        // 尝试选择自然排序最小的边;

        // 如果从该节点作为起点,可以将剩余所有的节点全部都走完

        // 如果将边看做点,将点之间的连通(机票可以连续),看做图的边呢？将图中所有的边都走一遍?
        // 那好像回到了原点; 还是求解所有的边都走一遍的问题

        // 第三轮: 实际上是带有权重的图,需要将权重全部变为0，每次从边上走过,则将边上的权重-1;
        // 每一次选择节点时, 编号自然排序最小的节点开始尝试;

        Map<String, LinkedList<String>> map = new HashMap<>();
        for(List<String> ticket : tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            if(!map.containsKey(from)){
                map.put(from, new LinkedList<>());
            }
            map.get(from).offer(to);
        }

        // 将map变为List<String>比较合适

        length = tickets.size() + 1;

        List<String> choosed = new ArrayList<String>(length);
        choosed.add("JFK");
        dfs(choosed, map);
        return choosed;
    }

    // node 和 choosed的最后一个元素一致,
    private boolean dfs(List<String> choosed, Map<String,LinkedList<String>> graph){
        String node = choosed.get(choosed.size()-1);
        if(choosed.size() == length){
            return true;
        }
        if(!graph.containsKey(node) || graph.get(node).isEmpty()){
            return false;
        }
        LinkedList<String> nextNodes = graph.get(node);
        int length = nextNodes.size();
        for(int i = 0; i < length; i++){
            String nextNode = nextNodes.get(i);
            choosed.add(nextNode);
            if(dfs(choosed, graph)){
                return true;
            }
            choosed.remove(choosed.size()-1);
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().findItinerary(Arrays.asList(
//                Arrays.asList("JFK","SFO"),
//                Arrays.asList("JFK","ATL"),
//                Arrays.asList("SFO","ATL"),
//                Arrays.asList("ATL","JFK"),
//                Arrays.asList("ATL","SFO"))));
        System.out.println(new Solution().findItinerary(Arrays.asList(
                Arrays.asList("JFK","KUL"),
                Arrays.asList("JFK","NRT"),
                Arrays.asList("NRT","JFK")
        )));


//        [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
    }
}
