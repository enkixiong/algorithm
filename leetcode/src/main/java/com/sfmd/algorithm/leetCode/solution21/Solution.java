package com.sfmd.algorithm.leetCode.solution21;

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode merged = new ListNode(-1);

        ListNode tail = new ListNode(-2);

        while(l1 != null || l2 != null){

            ListNode min = null;
            if (l2 == null || (l1 != null && l1.val < l2.val)){
                min = l1;
                l1 = l1.next;

            }else{
                min = l2;
                l2 = l2.next;
            }

            min.next = null;

            if (merged.next == null){
                merged.next = min;
            }

            if (tail.next == null){
                tail.next = min;
            }else{
                tail.next.next = min;
                tail.next = min;
            }
        }

        return merged.next;

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
            return val + "=>" + next;
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node7 = new ListNode(7);
        ListNode node9 = new ListNode(9);
        node1.next = node3;
        node3.next = node5;
        node5.next = node7;
        node7.next = node9;


        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);
        ListNode node10 = new ListNode(10);

        node2.next = node4;
        node4.next = node6;
        node6.next = node8;
        node8.next = node10;

//        System.out.println(new Solution().mergeTwoLists(node1,node2));
//        System.out.println(new Solution().mergeTwoLists(null,node2));
//        System.out.println(new Solution().mergeTwoLists(node1,null));
//        System.out.println(new Solution().mergeTwoLists(null,null));
        System.out.println(new Solution().mergeTwoLists(node9,node2));






    }

}
