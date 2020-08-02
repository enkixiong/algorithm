package com.sfmd.algorithm.leetCode.s82;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode result = sentinel;
        while(sentinel.next != null){
            if (sentinel.next.next == null){
                break;
            }
            if (sentinel.next.next.val != sentinel.next.val){
                sentinel = sentinel.next;
                continue;
            }
            int val = sentinel.next.val;

            ListNode current = sentinel.next;
            while(current !=null){
                if (current.val != val){
                    break;
                }
                current = current.next;
            }
            sentinel.next = current;
        }

        return result.next;
    }

    public static void main(String[] args) {

    }

    private static class ListNode { int val; ListNode next; ListNode(int x) { val = x; } }

}
