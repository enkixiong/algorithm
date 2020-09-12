package week_06.offer.offer38;

import java.util.*;

public class ContainsRepeatSolution {

    public String[] permutation(String s) {
        Map<Character,Integer> seed = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int count = seed.getOrDefault(c,0);
            seed.put(c, count+1);
        }
        Map<Character,Integer> choosed = new HashMap<>(seed.size());
        char[] generate = new char[s.length()];
        LinkedList<String> linkedList = new LinkedList<>();
        dfs(seed,choosed,generate,0,linkedList);
        String[] result = new String[linkedList.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = linkedList.removeFirst();
        }
        return result;
    }

    private void dfs(Map<Character, Integer> seed, Map<Character, Integer> choosed, char[] generate, int i, LinkedList<String> linkedList) {
        if(i == generate.length){
            linkedList.add(new String(generate));
            return;
        }
        Set<Character> keys = new HashSet<>(seed.keySet());
        for(Character key : keys){
            transfer(seed,choosed,key);
            generate[i] = key;
            dfs(seed, choosed, generate,i+1,linkedList);
            transfer(choosed,seed,key);
        }
    }

    private void transfer(Map<Character,Integer> map1, Map<Character,Integer> map2, Character c){
        int count = map1.get(c);
        if (count == 1){
            map1.remove(c);
        }else{
            map1.put(c, count-1);
        }
        count = map2.getOrDefault(c,0);
        map2.put(c, count+1);
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ContainsRepeatSolution().permutation("aab")));
    }


}
