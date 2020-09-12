package week_06.offer.offer37;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        int levelSize = 0;
        while(!deque.isEmpty()){
            levelSize = deque.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode node = deque.removeFirst();
                if(node == null){
                    builder.append('#').append(',');
                }else{
                    builder.append(node.val).append(',');
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                }
            }
        }
        return builder.substring(0, builder.length() - (levelSize<<1)-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        String[] split = data.split(",");
        TreeNode root = generateTreeNode(split[0]);
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);

        int offset = 1;
        while(offset < split.length){
            TreeNode node = deque.removeFirst();
            if(node == null){
                continue;
            }
            node.left = generateTreeNode(split[offset++]);
            node.right = generateTreeNode(split[offset++]);
            deque.addLast(node.left);
            deque.addLast(node.right);
        }
        return root;
    }

    private TreeNode generateTreeNode(String s){
        if("#".equals(s)){
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
    }

    public static void main(String[] args) {
        TreeNode node = new Codec().deserialize("1");
        System.out.println(new Codec().serialize(node));
    }
}
