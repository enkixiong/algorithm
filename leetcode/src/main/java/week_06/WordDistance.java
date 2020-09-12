package week_06;

import java.util.*;

class WordDistance {

    Map<String, List<Integer>> info;;

    public WordDistance(String[] words) {
        info = new HashMap<>(words.length);
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            if(!info.containsKey(word)){
                info.put(word, new ArrayList<>());
            }
            info.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> offsetList1 = info.get(word1);
        List<Integer> offsetList2 = info.get(word2);

        int offset1 = 0;
        int offset2 = 0;
        int min = Integer.MAX_VALUE;

        while(true){
            int distance = offsetList1.get(offset1) - offsetList2.get(offset2);
            min = Math.min(Math.abs(distance), min);
            if(distance < 0){
                offset1++;
                if(offset1 == offsetList1.size()){
                    break;
                }
            }else{
                offset2++;
                if(offset2 == offsetList2.size()){
                    break;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        WordDistance distance = new WordDistance(new String[]{"practice","makes","perfect","coding","makes"});
        System.out.println(distance.shortest("makes","coding"));
        StringBuilder builder = new StringBuilder();
        builder.append("1");
        builder.append("2");
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
        System.out.println(String.join("->", Arrays.asList("a","b")));
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
