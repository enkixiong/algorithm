package week_05.s314;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.*;

public class BFSSolution {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null){
            return Collections.emptyList();
        }

        int min = 0;
        Map<Integer,List<Integer>> resultCache = new HashMap<>();

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        Deque<Integer> indexDeque = new LinkedList<>();
        indexDeque.addLast(0);

        while(!deque.isEmpty()){
            int first = indexDeque.peekFirst();
            min = Math.min(first,min);
            if(!resultCache.containsKey(first)){
                resultCache.put(first, new ArrayList<>());
            }
            int last = indexDeque.peekLast();
            if(!resultCache.containsKey(last)){
                resultCache.put(last, new ArrayList<>());
            }

            int size = deque.size();
            for(int i = 0; i < size; i++){
                TreeNode node = deque.removeFirst();
                int index = indexDeque.removeFirst();
                resultCache.get(index).add(node.val);
                if(node.left != null){
                    deque.addLast(node.left);
                    indexDeque.addLast(index-1);
                }
                if(node.right != null){
                    deque.addLast(node.right);
                    indexDeque.addLast(index+1);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>(resultCache.size());
        for(int i = 0; i < resultCache.size(); i++){
            result.add(resultCache.get(min+i));
        }

        return result;
    }

}
