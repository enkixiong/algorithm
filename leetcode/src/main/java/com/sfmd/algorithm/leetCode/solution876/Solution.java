package com.sfmd.algorithm.leetCode.solution876;

import com.sfmd.algorithm.leetCode.ListNode;

public class Solution {

    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }

        ListNode node = new ListNode(-1);
        node.next = head;

        ListNode oneStepPointer = node;
        ListNode twoStepPointer = node;

        while(true){
            oneStepPointer = oneStepPointer.next;
            twoStepPointer = twoStepPointer.next.next;

            if (twoStepPointer.next == null || twoStepPointer.next.next == null){
                return oneStepPointer.next;
            }

        }

    }

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);
        ListNode node10 = new ListNode(10);
        ListNode node12 = new ListNode(12);


        node2.next = node4;
        node4.next = node6;
        node6.next = node8;
        node8.next = node10;
        node10.next = node12;

//        System.out.println(new Solution().middleNode(node12));
        System.out.println(new Solution().middleNode(node10));
//        System.out.println(new Solution().middleNode(node8));
//        System.out.println(new Solution().middleNode(node6));
//        System.out.println(new Solution().middleNode(node4));
//        System.out.println(new Solution().middleNode(node2));
//        System.out.println(new Solution().middleNode(node2));


    }
}
