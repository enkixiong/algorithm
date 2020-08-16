package com.sfmd.algorithm.leetCode.s449;

import com.sfmd.algorithm.leetCode.tree.TreeNode;
import javafx.util.Pair;

class DFSCodeC {

    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder()).substring(1);
    }

    private StringBuilder serialize(TreeNode node, StringBuilder builder) {
        builder.append(' ');
        if (node != null) {
            builder.append(node.val);
            serialize(node.left, builder);
            serialize(node.right, builder);
        } else {
            builder.append('#');
        }

        return builder;
    }

    public TreeNode deserialize(String data) {
        return deserialize(data.toCharArray());
    }

    // 该序列化方法,唯一的问题在于: 启用了变量index
    // 有三种办法取消index -->
    // 1. 将data直接转为Deque;
    // 2. 更改方法的入参和出参; 入参是当前子树的父亲节点,左节点还是右节点, 待处理数据,目前数据处理到的位置; 出参是目前已经扫描到的位置;
    // 3. 返参包含 待扫描位置+当前构造出来的子树
    private TreeNode deserialize(char[] data) {
        return deserialize(data,0).node;
    }

    private Info deserialize(char[] data, int offset){
        if (data[offset] == '#') {
            // 2的含义是: '# '; #号加上空格
//            offset += 2;
            return new Info(null, offset+2);
        }

        // 设置符号位
        int flag = 1;
        if(data[offset] == '-'){
            flag = -1;
            offset++;
        }

        // 取当前值
        int value = 0;
        while (data[offset] != ' ') {
            value = value * 10 + data[offset++] - '0';
        }
        // 跳过空格,指向左节点开始的位置
        ++offset;
        TreeNode node =  new TreeNode(flag * value);
        Info left = deserialize(data, offset);
        Info right = deserialize(data, left.nextOffset);
        node.left = left.node;
        node.right = right.node;
        return new Info(node, right.nextOffset);
    }

    private static class Info {
        TreeNode node;
        int nextOffset;

        public Info(TreeNode node, int nextOffset) {
            this.node = node;
            this.nextOffset = nextOffset;
        }
    }




}
