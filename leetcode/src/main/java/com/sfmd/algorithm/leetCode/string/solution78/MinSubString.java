package com.sfmd.algorithm.leetCode.string.solution78;

import java.util.HashMap;
import java.util.Map;

public class MinSubString {

    public String minWindow(String s, String t) {

        // 边界值检测
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length()) {
            return "";
        }

        if (s.contains(t)) {
            return t;
        }

        int[] pair = new int[]{0, Integer.MAX_VALUE};
        RepeatableLRU deque = new RepeatableLRU(t);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            Node node = new Node(c, i);
            deque.push(node);
            if (deque.getSize() == t.length()) {
                int startIndex = deque.getLast().offset;
                int endIndex = deque.getFirst().offset;
                if (endIndex - startIndex < pair[1] - pair[0]) {
                    pair[0] = startIndex;
                    pair[1] = endIndex;
                }
                deque.removeLast();
            }
        }
        if (pair[1] == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(pair[0], pair[1] + 1);
    }

    private static class RepeatableLRU {

        Map<Character, Integer> currentCount = new HashMap<>();

        Map<Character, Integer> countMap;

        int count = 0;

        private final Node head = new Node('h', -1);

        private final Node tail = new Node('t', -1);

        public RepeatableLRU(String t) {
            countMap = new HashMap<>();
            int i = 0;
            for (char charValue : t.toCharArray()) {
               countMap.put(charValue, countMap.getOrDefault(charValue,0)+1);
            }
            head.next = tail;
            tail.pre = head;
        }

        private void push(Node node) {

            if (!countMap.containsKey(node.charValue)) {
                return;
            }

            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
            currentCount.put(node.charValue, currentCount.getOrDefault(node.charValue, 0) + 1);
            count++;

            if (currentCount.get(node.charValue) > countMap.get(node.charValue)) {
                Node delete = tail.pre;
                while (delete != head) {
                    if (delete.charValue == node.charValue) {
                        delete.next.pre = delete.pre;
                        delete.pre.next = delete.next;
                        count--;
                        currentCount.put(node.charValue, currentCount.getOrDefault(node.charValue, 0) - 1);
                        break;
                    }
                    delete = delete.pre;
                }
            }
        }

        private Node getFirst() {
            return head.next;
        }

        private Node getLast() {
            return tail.pre;
        }

        private Node removeLast() {
            Node node = tail.pre;
            tail.pre = node.pre;
            node.pre.next = tail;
            count--;
            currentCount.put(node.charValue, currentCount.getOrDefault(node.charValue, 0) - 1);
            return node;
        }

        private int getSize() {
            return count;
        }

        private int getCount(char c) {
            return currentCount.getOrDefault(c, 0);
        }

        @Override
        public String toString() {
            return head.toString();
        }
    }

    private static class Node {
        private final char charValue;
        private final int offset;
        private Node next;
        private Node pre;

        public Node(char charValue, int offset) {
            this.charValue = charValue;
            this.offset = offset;
        }

        @Override
        public String toString() {
            return String.format("(%s, %d) ==> %s", charValue, offset, next);
        }
    }

    public static void main(String[] args) {

        System.out.println(new MinSubString().minWindow("ADOBECODEBANC","ABC"));
        System.out.println(new MinSubString().minWindow("ADOBECODEBANC",""));
        System.out.println(new MinSubString().minWindow("ADOBECODEBANC","D"));
        System.out.println(new MinSubString().minWindow("ADOBECODEBANC","G"));
        System.out.println(new MinSubString().minWindow("ABA","AA"));
        System.out.println(new MinSubString().minWindow("bbaa","aba"));
        System.out.println(new MinSubString().minWindow("acbbaca","aba"));
        System.out.println(new MinSubString().minWindow("acbdbaab", "aabd"));


//        "acbdbaab"
//        "aabd"


    }


}
