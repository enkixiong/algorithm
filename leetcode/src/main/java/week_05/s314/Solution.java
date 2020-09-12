package week_05.s314;

import com.alibaba.fastjson.JSON;
import com.sfmd.algorithm.leetCode.s449.LeetcodeCodec;
import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.*;

public class Solution {

    private int min;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer,List<Info>> map = new HashMap<>();
        preOrderVisit(root,0,0,map);

        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

        List<List<Integer>> results = new ArrayList<>(keyList.size());
        for(int i = 0; i < keyList.size(); i++){
            List<Info> infoList = map.get(i+min);
            infoList.sort(new Comparator<Info>(){
                public int compare(Info o1, Info o2){
                    if (o1.level != o2.level){
                        return o1.level - o2.level;
                    }else{
                        return o1.index - o2.index;
                    }
                }
            });
            List<Integer> result = new ArrayList<>(infoList.size());
            for(Info info : infoList){
                result.add(info.val);
            }
            results.add(result);
        }
        return results;
    }

    private void preOrderVisit(TreeNode node, int index, int level, Map<Integer,List<Info>> map){

        // termonator
        if (node == null){
            return;
        }
        min = Math.min(min, index);
        // handle current level
        if(!map.containsKey(index)){
            map.put(index, new ArrayList<>());
        }
        map.get(index).add(new Info(level,index,node.val));

        // drill down
        level++;
        preOrderVisit(node.left, index-1, level,map);
        preOrderVisit(node.right, index+1, level,map);
    }

    private static class Info {
        private int index;
        private int level;
        private int val;

        private Info(int level, int index, int val){
            this.level = level;
            this.index = index;
            this.val = val;
        }

    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(
                new BFSSolution().verticalOrder(new LeetcodeCodec().deserialize("3,9,20,null,null,15,7")))
        );
        System.out.println(JSON.toJSONString(
                new BFSSolution().verticalOrder(new LeetcodeCodec().deserialize("3,9,8,4,0,1,7")))
        );
        System.out.println(JSON.toJSONString(
                new BFSSolution().verticalOrder(new LeetcodeCodec().deserialize("3,9,8,4,0,1,7,null,null,null,2,5")))
        );
    }

}
