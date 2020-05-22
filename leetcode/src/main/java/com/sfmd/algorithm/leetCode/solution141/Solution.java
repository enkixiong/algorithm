package com.sfmd.algorithm.leetCode.solution141;

public class Solution {


    public boolean hasCycle(ListNode head) {

        if (head == null){
            return false;
        }

        ListNode oneStepPoint = head;
        ListNode twoStepPoint = head;
        while(true){
            if (oneStepPoint.next == null){
                return false;
            }
            oneStepPoint = oneStepPoint.next;
            if (twoStepPoint.next == null || twoStepPoint.next.next == null){
                return false;
            }
            twoStepPoint = twoStepPoint.next.next;
            if (oneStepPoint == twoStepPoint){
                return true;
            }
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}