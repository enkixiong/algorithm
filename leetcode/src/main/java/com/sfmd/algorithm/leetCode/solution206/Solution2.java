package com.sfmd.algorithm.leetCode.solution206;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution2 {

    public ListNode reverseList(ListNode head) {

        // 哨兵
        ListNode begin = new ListNode(-1);
        begin.next = head;

        ListNode reversed = new ListNode(-2);

        ListNode transfer = null;

        while(begin.next != null){
            transfer = begin.next;
            begin.next = begin.next.next;

            transfer.next = reversed.next;
            reversed.next = transfer;

        }

        return reversed.next;
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
        System.out.println(new Solution2().reverseList(node1));
//        System.out.println(new Solution().reverseList(node2));
//        System.out.println(new Solution().reverseList(node3));
//        System.out.println(new Solution2().reverseList(node4));
//        System.out.println(new Solution().reverseList(node5));





    }
}
