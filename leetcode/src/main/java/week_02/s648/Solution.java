package week_02.s648;

import java.util.Arrays;
import java.util.List;

class Solution {
    public String replaceWords(List<String> dict, String sentence) {

        Trie trie = new Trie();
        for(String s : dict){
            trie.add(s);
        }
        char[] main = sentence.toCharArray();
        return new String(main, 0, getShortestRoot(trie.root, main));
    }

    private int getShortestRoot(TrieNode root, char[] main) {


        int scan = 0;
        int writeback = 0;


        TrieNode node = root;
        // 当前单词有几种可能:
        // 1. 单词不在字典中,并且与Trie树无重合
        // 2. 单词不在字典中,并且与Trie树有重合(比词根短)
        // 3. 单词是字典树的派生词
        while (scan < main.length) {
            char c = main[scan];
            // 如果该单词在字典中无词根, 并且部分重合,并且主串先结束时
            if (c == ' ') {
                main[writeback++] = ' ';
                scan++;
                node = root;
                continue;
            }

            int offset = c - 'a';
            TrieNode nextNode = node.children[offset];

            // 如果该单词在字典中无词根, 并且部分重合,并且主串后结束时
            if (nextNode == null) {
                // 走到下一个单词,并且将 node 重置
                while (scan < main.length) {
                    main[writeback++] = main[scan];
                    if (main[scan++] == ' ') {
                        node = root;
                        break;
                    }
                }
                continue;
            }
            // 写入该字符
            main[writeback++] = main[scan++];
            if(nextNode.isLeaf){
                while(scan < main.length){
                    if(main[scan++] == ' '){
                        main[writeback++] = ' ';
                        break;
                    }
                }
                node = root;
                continue;
            }
            node = nextNode;
        }

        return writeback;
    }

    private static class Trie {

        private final TrieNode root = new TrieNode();

        public void add(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int offset = c - 'a';
                if (node.children[offset] == null) {
                    node.children[offset] = new TrieNode();
                }
                node = node.children[offset];
            }
            node.isLeaf = true;
        }

    }

    private static class TrieNode {

        private boolean isLeaf;
        private final TrieNode[] children;

        private TrieNode() {
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(new Solution().replaceWords(Arrays.asList(        "e","k","c","harqp","h","gsafc","vn","lqp","soy","mr","x","iitgm","sb","oo","spj","gwmly","iu","z","f","ha","vds","v","vpx","fir","t","xo","apifm","tlznm","kkv","nxyud","j","qp","omn","zoxp","mutu","i","nxth","dwuer","sadl","pv","w","mding","mubem","xsmwc","vl","farov","twfmq","ljhmr","q","bbzs","kd","kwc","a","buq","sm","yi","nypa","xwz","si","amqx","iy","eb","qvgt","twy","rf","dc","utt","mxjfu","hm","trz","lzh","lref","qbx","fmemr","gil","go","qggh","uud","trnhf","gels","dfdq","qzkx","qxw"
        ),         "ikkbp miszkays wqjferqoxjwvbieyk gvcfldkiavww vhokchxz dvypwyb bxahfzcfanteibiltins ueebf lqhflvwxksi dco kddxmckhvqifbuzkhstp wc ytzzlm gximjuhzfdjuamhsu gdkbmhpnvy ifvifheoxqlbosfww mengfdydekwttkhbzenk wjhmmyltmeufqvcpcxg hthcuovils ldipovluo aiprogn nusquzpmnogtjkklfhta klxvvlvyh nxzgnrveghc mpppfhzjkbucv cqcft uwmahhqradjtf iaaasabqqzmbcig zcpvpyypsmodtoiif qjuiqtfhzcpnmtk yzfragcextvx ivnvgkaqs iplazv jurtsyh gzixfeugj rnukjgtjpim hscyhgoru aledyrmzwhsz xbahcwfwm hzd ygelddphxnbh rvjxtlqfnlmwdoezh zawfkko iwhkcddxgpqtdrjrcv bbfj mhs nenrqfkbf spfpazr wrkjiwyf cw dtd cqibzmuuhukwylrnld dtaxhddidfwqs bgnnoxgyynol hg dijhrrpnwjlju muzzrrsypzgwvblf zbugltrnyzbg hktdviastoireyiqf qvufxgcixvhrjqtna ipfzhuvgo daee r nlipyfszvxlwqw yoq dewpgtcrzausqwhh qzsaobsghgm ichlpsjlsrwzhbyfhm ksenb bqprarpgnyemzwifqzz oai pnqottd nygesjtlpala qmxixtooxtbrzyorn gyvukjpc s mxhlkdaycskj uvwmerplaibeknltuvd ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp"
        ));
//        ["a","b","c"]
//        "aadsfasf absbs bbab cadsfafs"


//        "e","k","c","harqp","h","gsafc","vn","lqp","soy","mr","x","iitgm","sb","oo","spj","gwmly","iu","z","f","ha","vds","v","vpx","fir","t","xo","apifm","tlznm","kkv","nxyud","j","qp","omn","zoxp","mutu","i","nxth","dwuer","sadl","pv","w","mding","mubem","xsmwc","vl","farov","twfmq","ljhmr","q","bbzs","kd","kwc","a","buq","sm","yi","nypa","xwz","si","amqx","iy","eb","qvgt","twy","rf","dc","utt","mxjfu","hm","trz","lzh","lref","qbx","fmemr","gil","go","qggh","uud","trnhf","gels","dfdq","qzkx","qxw"
//        "ikkbp miszkays wqjferqoxjwvbieyk gvcfldkiavww vhokchxz dvypwyb bxahfzcfanteibiltins ueebf lqhflvwxksi dco kddxmckhvqifbuzkhstp wc ytzzlm gximjuhzfdjuamhsu gdkbmhpnvy ifvifheoxqlbosfww mengfdydekwttkhbzenk wjhmmyltmeufqvcpcxg hthcuovils ldipovluo aiprogn nusquzpmnogtjkklfhta klxvvlvyh nxzgnrveghc mpppfhzjkbucv cqcft uwmahhqradjtf iaaasabqqzmbcig zcpvpyypsmodtoiif qjuiqtfhzcpnmtk yzfragcextvx ivnvgkaqs iplazv jurtsyh gzixfeugj rnukjgtjpim hscyhgoru aledyrmzwhsz xbahcwfwm hzd ygelddphxnbh rvjxtlqfnlmwdoezh zawfkko iwhkcddxgpqtdrjrcv bbfj mhs nenrqfkbf spfpazr wrkjiwyf cw dtd cqibzmuuhukwylrnld dtaxhddidfwqs bgnnoxgyynol hg dijhrrpnwjlju muzzrrsypzgwvblf zbugltrnyzbg hktdviastoireyiqf qvufxgcixvhrjqtna ipfzhuvgo daee r nlipyfszvxlwqw yoq dewpgtcrzausqwhh qzsaobsghgm ichlpsjlsrwzhbyfhm ksenb bqprarpgnyemzwifqzz oai pnqottd nygesjtlpala qmxixtooxtbrzyorn gyvukjpc s mxhlkdaycskj uvwmerplaibeknltuvd ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp"
    }

}
