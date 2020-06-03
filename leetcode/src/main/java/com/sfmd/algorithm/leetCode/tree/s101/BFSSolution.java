package com.sfmd.algorithm.leetCode.tree.s101;

import java.util.Comparator;

public class BFSSolution {

    public boolean isSymmetric(TreeNode root) {

        // 广度优先遍历
        Comparator<TreeNode> comparator = new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                if (o1 == null && o2 == null){
                    return 0;
                }
                if (o1 == null){
                    return 1;
                }
                if (o2 == null){
                    return -1;
                }
                return o1.val - o2.val;
            }
        };
        DoubleEndedQueue<TreeNode> queue = new DoubleEndedQueue<>();
        queue.push(root);
        int currentLevelCount = 1;
        while(!queue.isEmpty()){
            if (!queue.isSymmetric(comparator)){
                return false;
            }
            while(currentLevelCount != 0){
                TreeNode node = queue.pop();
                currentLevelCount--;
                if (node == null){
                    continue;
                }
                queue.push(node.left);
                queue.push(node.right);
            }
            currentLevelCount = queue.size;
        }
        return true;
    }

    private static class DoubleEndedQueue<T> {

        private final Node<T> head;
        private final Node<T> tail;

        int size;

        public DoubleEndedQueue() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.pre = head;
             size = 0;
        }

        public void push(T t){
            Node<T> node = new Node<>();
            node.value = t;
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            size++;
        }

        public T pop(){
            Node<T> last = tail.pre;

            tail.pre = last.pre;
            last.pre.next = tail;
            size--;

            return last.value;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public boolean isSymmetric(Comparator<T> comparator){
            if (size == 0 || size == 1){
                return true;
            }

            Node<T> first = head.next;
            Node<T> last = tail.pre;

            for (int i = 0; i < size/2; i++) {
                if (comparator.compare(first.value, last.value) != 0) {
                    return false;
                }
                first = first.next;
                last = last.pre;
            }
            return true;
        }

    }

    private static class Node<T>{
        private T value;
        private Node<T> pre;
        private Node<T> next;

    }


    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n11 = new TreeNode(3);
        TreeNode n12 = new TreeNode(4);
        TreeNode n21 = new TreeNode(4);
        TreeNode n22 = new TreeNode(3);

        n0.left = n1;
        n0.right = n2;
        n1.left = n11;
        n1.right = n12;
        n2.left = n21;
        n2.right = n22;

        System.out.println(new BFSSolution().isSymmetric(n0));

//        TreeNode n0 = new TreeNode(1);
//        TreeNode n1 = new TreeNode(2);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n12 = new TreeNode(4);
//        TreeNode n22 = new TreeNode(4);
//
//        n0.left = n1;
//        n0.right = n2;
//        n1.right = n12;
//        n2.right = n22;
//
//        System.out.println(new SymmetryBinaryTree().isSymmetric(n0));

    }

}

