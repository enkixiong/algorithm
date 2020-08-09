package week_02.s720;

class Solution {
    public String longestWord(String[] words) {

        TrieNode root = new TrieNode(true);
        for(String word : words){
            addToTrieTree(root,word);
        }

        String[] candidates = new String[30];
        char[] seed = new char[30];

        dfs(root, 0, candidates, seed);

        for(int i = 29; i >= 0; i--){
            if(candidates[i] != null){
                return candidates[i];
            }
        }
        return "";
    }

    private void dfs(TrieNode node, int level, String[] candidates, char[] seed){
        for(int i = 0; i < 26; i++){
            if(node.children[i] != null && node.children[i].isLeaf){
                seed[level] = (char)(i + 'a');
                if(candidates[level] == null){
                    candidates[level] = new String(seed, 0, level+1);
                }
                dfs(node.children[i], level+1, candidates, seed);
            }
        }
    }

    private void addToTrieTree(TrieNode root, String word){
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int offset = c - 'a';
            if (node.children[offset] == null){
                node.children[offset] = new TrieNode(false);
            }
            node = node.children[offset];
        }
        node.isLeaf = true;
    }

    private static class TrieNode {
        private boolean isLeaf;
        private final TrieNode[] children;

        private TrieNode (boolean isLeaf){
            this.isLeaf = isLeaf;
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestWord(new String[]{"w","wo","wor","worl","world"}));
        System.out.println(new Solution().longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));

        System.out.println(new Solution().longestWord(new String[]{"a", "b", "bb"}));
        System.out.println(new Solution().longestWord(new String[]{"a", "b"}));

    }
}
