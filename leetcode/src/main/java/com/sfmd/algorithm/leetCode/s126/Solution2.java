package com.sfmd.algorithm.leetCode.s126;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Solution2 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        // 利用 BFS 得到所有的邻居节点
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs(beginWord, endWord, wordList, map);
        ArrayList<String> temp = new ArrayList<String>();
        // temp 用来保存当前的路径
        temp.add(beginWord);
        findLaddersHelper(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                   ArrayList<String> temp, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }
        // 得到所有的下一个的节点
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    //利用递归实现了双向搜索
    private void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {
        Set<String> set1 = new HashSet<String>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<String>();
        set2.add(endWord);
        Set<String> wordSet = new HashSet<String>(wordList);
        bfsHelper(set1, set2, wordSet, true, map);
    }

    // direction 为 true 代表向下扩展，false 代表向上扩展
    private boolean bfsHelper(Set<String> set1, Set<String> set2, Set<String> wordSet, boolean direction,
                              HashMap<String, ArrayList<String>> map) {
        //set1 为空了，就直接结束
        //比如下边的例子就会造成 set1 为空
    /*	"hot"
		"dog"
		["hot","dog"]*/
        if(set1.isEmpty()){
            return false;
        }
        // set1 的数量多，就反向扩展
        if (set1.size() > set2.size()) {
            return bfsHelper(set2, set1, wordSet, !direction, map);
        }
        // 将已经访问过单词删除
        wordSet.removeAll(set1);
        wordSet.removeAll(set2);

        boolean done = false;

        // 保存新扩展得到的节点
        Set<String> set = new HashSet<String>();

        for (String str : set1) {
            //遍历每一位
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // 尝试所有字母
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if(chars[i] == ch){
                        continue;
                    }
                    chars[i] = ch;

                    String word = new String(chars);

                    // 根据方向得到 map 的 key 和 val
                    String key = direction ? str : word;
                    String val = direction ? word : str;

                    ArrayList<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

                    //如果相遇了就保存结果
                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }

                    //如果还没有相遇，并且新的单词在 word 中，那么就加到 set 中
                    if (!done && wordSet.contains(word)) {
                        set.add(word);
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }

        //一般情况下新扩展的元素会多一些，所以我们下次反方向扩展  set2
        return done || bfsHelper(set2, set, wordSet, !direction, map);

    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution2().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))));
        System.out.println(JSON.toJSONString(new Solution2().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","dog","lot","cog")))));
        System.out.println(JSON.toJSONString(new Solution2().findLadders("hit","cog",
                new ArrayList<>(Arrays.asList("hot","dot","lot","cog")))));
        System.out.println(JSON.toJSONString(new Solution2().findLadders("a","c",
                new ArrayList<>(Arrays.asList("a","b","c")))));
        System.out.println(JSON.toJSONString(new Solution2().findLadders("cet","ism",
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
