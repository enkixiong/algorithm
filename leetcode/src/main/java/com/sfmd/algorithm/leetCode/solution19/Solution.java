package com.sfmd.algorithm.leetCode.solution19;

import com.sfmd.algorithm.leetCode.ListNode;

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode list = reverseList(head);

        int i = 0;

        // 哨兵
        ListNode begin = new ListNode(-1);
        begin.next = list;

        ListNode reversed = new ListNode(-2);

        ListNode transfer = null;

        while(true){

            i++;

            if (i == n){
                begin = begin.next;
            }

            if (begin.next == null){
                break;
            }

            transfer = begin.next;
            begin.next = begin.next.next;

            transfer.next = reversed.next;
            reversed.next = transfer;

        }

        return reversed.next;

    }

    public ListNode reverseList(ListNode list) {

        // 哨兵
        ListNode begin = new ListNode(-1);
        begin.next = list;

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

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);
        ListNode node10 = new ListNode(10);

        node2.next = node4;
        node4.next = node6;
        node6.next = node8;
        node8.next = node10;

        System.out.println(new Solution().removeNthFromEnd(node2, 0));
        System.out.println(new Solution().removeNthFromEnd(node10, 1));

    }
}
