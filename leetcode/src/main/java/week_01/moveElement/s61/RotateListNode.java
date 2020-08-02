package week_01.moveElement.s61;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class RotateListNode {

    public ListNode rotateRight(ListNode head, int k) {

        int len = 0;
        ListNode node = head;
        ListNode tail = node;
        while (node != null) {
            len++;
            tail = node;
            node = node.next;
        }

        if (len <= 1) {
            return head;
        }

        int step = k % len;
        if (step == 0) {
            return head;
        }

        // 找到指向开始反转的节点
        ListNode newTail = head;
        for (int i = len - step - 1; i > 0; i--) {
            newTail = newTail.next;
        }

        ListNode kthHead = newTail.next;
        newTail.next = null;
        tail.next = head;


        return kthHead;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.format("%d -> %s", val, next);
        }
    }

    private static ListNode buildNodes(int[] nums) {
        ListNode sentinel = new ListNode(-1);
        ListNode tail = sentinel;
        for (int num : nums) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return sentinel.next;
    }


    public static void main(String[] args) {
        System.out.println(new RotateListNode().rotateRight(buildNodes(new int[]{1, 2, 3, 4, 5}), 2));
        System.out.println(new RotateListNode().rotateRight(buildNodes(new int[]{1, 2, 3, 4, 5}), 1));

    }

}
