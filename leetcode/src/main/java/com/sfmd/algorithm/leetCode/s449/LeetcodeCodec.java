package com.sfmd.algorithm.leetCode.s449;

import com.sfmd.algorithm.leetCode.tree.TreeNode;

import java.util.*;

public class LeetcodeCodec {

    public String serialize(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        //  初始化层
        deque.addLast(root);

        while(!deque.isEmpty()){
            // 标记当前层
            int levelSize = deque.size();
            // 将当前层全部序列化
            for(int i = 0; i < levelSize; i++){
                TreeNode node = deque.removeLast();
                if(node == null) {
                    builder.append(",null");
                }else{
                    // 并将下一层的值加入队列
                    builder.append(',').append(node.val);
                    deque.addFirst(node.left);
                    deque.addFirst(node.right);
                }
            }
        }
        // 截取第一个分隔符
        return builder.substring(1);
    }

    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        String[] split = data.split(",");
        Deque<TreeNode> deque = new LinkedList<>();
        // 初始化层
        TreeNode root = createNode(deque, split[0]);
        // 开始层次反序列化
        levelDeserialize(deque, split, 1);
        return root;
    }

    public void levelDeserialize(Deque<TreeNode> deque, String[] data, int scan){

        // 反序列化
        while(!deque.isEmpty()){
            int levelSize = deque.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode node = deque.removeLast();
                if(scan >= data.length){
                    return;
                }
                node.left = createNode(deque,data[scan++]);
                if(scan >= data.length){
                    return;
                }
                node.right = createNode(deque, data[scan++]);
            }
        }
    }

    private TreeNode createNode(Deque<TreeNode> deque, String rightStr) {
        if(!"null".equals(rightStr)){
            TreeNode node = new TreeNode(Integer.parseInt(rightStr));
            deque.addFirst(node);
            return node;
        }
        return null;
    }

    public static void main(String[] args) {
        LeetcodeCodec s = new LeetcodeCodec();
        TreeNode node = s.deserialize("41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23");
//        TreeNode node = s.deserialize("2,1,3");
        System.out.println("层次遍历:"+s.serialize(node));
        String preOrder = new PreOrderSolution().serialize(node);
        System.out.println("前序:"+preOrder);
        TreeNode preNode = new PreOrderSolution().deserialize(preOrder);
        System.out.println("前序:"+new PreOrderSolution().serialize(preNode));
        DFSCodeC codec = new DFSCodeC();
        System.out.println("前序,空值以#:"+codec.serialize(preNode));
//        System.out.println(codec.index);
        System.out.println("前序,空值以#:"+codec.serialize(codec.deserialize(codec.serialize(preNode))));

//        BFSCodec codec = new BFSCodec();
//        System.out.println(codec.serialize(codec.deserialize("1,2,3,#,#,4,5,#,#,#,#")));
    }



}
