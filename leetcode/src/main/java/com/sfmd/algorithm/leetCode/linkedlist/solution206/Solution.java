package com.sfmd.algorithm.leetCode.linkedlist.solution206;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode reverseList(ListNode head) {

        // 哨兵
        ListNode begin = new ListNode(-1);
        begin.next = head;
        reverseSubList(begin);
        return begin.next;
    }

    public void reverseSubList(ListNode node){

        if(node == null || node.next == null){
            return;
        }

        reverseSubList(node.next);

        ListNode current = node;
        while(current.next != null){
            swap(current);
            current = current.next;
        }

    }

    /**
     * 交换该节点之后的两个节点
     * @param node0:当前节点
     */
    private void swap(ListNode node0){
        if (node0.next == null || node0.next.next == null){
            return;
        }
        ListNode node1 = node0.next;
        ListNode node2 = node0.next.next;
        node0.next = node1.next;
        node1.next = node2.next;
        node2.next = node1;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return
                    val +
                    "=>" + next;
        }
    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(node1);
//        System.out.println(new Solution().reverseList(node1));
//        System.out.println(new Solution().reverseList(node2));
//        System.out.println(new Solution().reverseList(node3));
        System.out.println(new Solution().reverseList(node4));
//        System.out.println(new Solution().reverseList(node5));





    }
}
