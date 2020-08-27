package week_04.s126;

import java.util.*;

public class DFSSolution {

    private int min = Integer.MAX_VALUE;

    private List<String> wordList;

    private int target = -1;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        this.wordList = wordList;
        wordList.add(beginWord);

        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)){
                target = i;
                break;
            }
        }
        if(target == -1){
            return Collections.emptyList();
        }

        // 构造图(包含起点/终点)
        int[][] graph = new int[wordList.size()][wordList.size()];
        for(int i = 0; i < wordList.size(); i++){
            for(int j = i+1; j < wordList.size(); j++){
                if(canTransfer(wordList.get(i),wordList.get(j))){
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }

        Map<Integer,List<List<String>>> result = new HashMap<>();
        // DFS

        int begin = wordList.size()-1;
        List<Integer> choosed = new ArrayList<>();
        choosed.add(begin);
        boolean[] visited = new boolean[wordList.size()];
        visited[begin] = true;
        dfs(choosed, visited, graph, result);
        return result.getOrDefault(min, Collections.emptyList());
    }

    private boolean canTransfer(String a, String b){
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count == 1;
    }

    private void dfs(List<Integer> route, boolean[] visited, int[][] graph, Map<Integer,List<List<String>>> result) {
        // terminator:剪枝
        if (route.size() > min){
            return;
        }

        int lastVisit = route.get(route.size()-1);
        // terminator 2
        if(lastVisit == target){
            min = route.size();
            List<String> singleResult = new ArrayList<>();
            for (Integer r : route) {
                singleResult.add(wordList.get(r));
            }
            List<List<String>> list = result.getOrDefault(min, new ArrayList<>());
            list.add(singleResult);
            result.put(min,list);
            return;
        }

        for(int j = 0; j < visited.length; j++){
            if(graph[lastVisit][j] == 1 && !visited[j]){
                route.add(j);
                visited[j] = true;
                dfs(route, visited, graph, result);
                visited[j] = false;
                route.remove(route.size()-1);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new DPSolution().findLadders("hit",
                "cog",
                        new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))));

        System.out.println(new DPSolution().findLadders("qa",
                "sq",
                new ArrayList<>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"))));

    }

}
