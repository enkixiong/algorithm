package com.sfmd.algorithm.leetCode.s126;

import com.alibaba.fastjson.JSON;

import java.util.*;


public class FindLadder {

    /**
     * BFS 广度优先所搜
     *
     * 数据结构: 图
     * @param beginWord : 开始的单词
     * @param endWord : 结束的单词
     * @param wordList : 可以转换的路径
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (wordList == null || wordList.isEmpty() || !wordList.contains(endWord)){
            return Collections.emptyList();
        }

        // 将顶点加入图
        Graph<String> graph = new Graph<>(wordList.size()+1);
        graph.addNode(beginWord);
        for (String word : wordList) {
            graph.addNode(word);
        }

        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        // 将边加入图
        for (int i = 0; i < wordList.size(); i++){
            String s1 = wordList.get(i);
            for (int j = i; j < wordList.size(); j++){
                String s2 = wordList.get(j);
                if (canConvert(s1, s2)){
                    graph.addEdge(s1,s2);
                }
            }
        }

        // 从顶点开始 BFS
        List<List<String>> resultList = new ArrayList<>();

        Node<String> start = graph.getNode(beginWord);


        List<List<Node<String>>> current = new ArrayList<>();
        List<List<Node<String>>> next = new ArrayList<>();

        List<Node<String>> startList = new ArrayList<>();
        startList.add(start);
        current.add(startList);
        boolean canNext = true;
        boolean reachNewNode = true;
        Node<String> end = graph.getNode(endWord);

        while(canNext && reachNewNode){
            reachNewNode = false;
            for (List<Node<String>> nodeList : current){
                Node<String> last = nodeList.get(nodeList.size()-1);
                List<Node<String>> nextList = graph.getEdgeList(last.value);
                if (nextList.contains(end)){
                    canNext = false;
                }
                for (Node<String> node : nextList) {
                    // 不允许加入已经访问过的顶点
                    if (nodeList.contains(node)){
                        continue;
                    }
                    List<Node<String>> generated = new ArrayList<>(nodeList);
                    generated.add(node);
                    next.add(generated);

                    reachNewNode = true;
                }
            }

            List<List<Node<String>>> tmp = current;
            current = next;
            next = tmp;
            next.clear();

        }

        for (List<Node<String>> candidate : current){
            if (candidate.get(candidate.size()-1) != end){
                continue;
            }
            List<String> result = new ArrayList<>(candidate.size());
            for (Node<String> stringNode : candidate) {
                result.add(stringNode.value);
            }
            resultList.add(result);
        }

        return resultList;

    }

    private boolean canConvert(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int notEqualsCount = 0;
        for (int i = 0; i < c1.length; i++){
            if (c1[i] != c2[i]){
                if (++notEqualsCount > 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static class Graph<T> {

        int size;

        private final Map<T,Node<T>> nodeMap;

        private final Map<Node<T>, List<Node<T>>> edgeMap;

        public Graph(int size) {
            this.size = size;
            nodeMap = new HashMap<>(size);
            edgeMap = new HashMap<>(size);
        }

        public void addNode(T t){
            Node<T> node = new Node<>(t);
            nodeMap.put(t, node);
            edgeMap.put(node, new ArrayList<>());
        }

        public void addEdge(T t1, T t2) {
            edgeMap.get(nodeMap.get(t1)).add(nodeMap.get(t2));
            edgeMap.get(nodeMap.get(t2)).add(nodeMap.get(t1));
        }

        public List<Node<T>> getEdgeList(T t){
            return edgeMap.get(nodeMap.get(t));
        }

        public List<Node<T>> getNodeList(){
            return new ArrayList<>(nodeMap.values());
        }

        public Node<T> getNode(T t){
            return nodeMap.get(t);
        }
    }

    private static class Node<T> {

        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new FindLadder().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))));
        System.out.println(JSON.toJSONString(new FindLadder().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","dog","lot","cog")))));
        System.out.println(JSON.toJSONString(new FindLadder().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","lot","cog")))));
        System.out.println(JSON.toJSONString(new FindLadder().findLadders("a","c",
                new ArrayList<>(Arrays.asList("a","b","c")))));
        System.out.println(JSON.toJSONString(new FindLadder().findLadders("cet","ism",
                new ArrayList<>(Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay",
                        "sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay",
                        "eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum",
                        "ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez",
                        "own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box",
                        "ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply",
                        "awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed",
                        "non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log",
                        "tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus",
                        "sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei",
                        "wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag",
                        "hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy",
                        "sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew",
                        "web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere",
                        "dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye",
                        "ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx",
                        "jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus",
                        "rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash",
                        "oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue",
                        "thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you",
                        "its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep",
                        "owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex",
                        "via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog",
                        "ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix",
                        "hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw",
                        "wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin",
                        "mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep",
                        "bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo",
                        "cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec",
                        "leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc",
                        "moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid",
                        "pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did",
                        "tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag",
                        "mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap",
                        "gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan",
                        "mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego",
                        "mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh",
                        "ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac",
                        "fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo",
                        "lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor",
                        "ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid",
                        "mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air",
                        "pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten",
                        "mob")))));
    }

}
