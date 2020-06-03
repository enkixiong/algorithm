package com.sfmd.algorithm.leetCode.stack.s146;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private final int size;
    private final Node head;
    private final Node tail;
    private final Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.size = capacity;
        head = new Node(-1,-1);
        tail = new Node(-2,-2);
        head.next = tail;
        tail.pre = head;
        cache = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null){
            return -1;
        }
        if (node.pre == head){
            return node.value;
        }
        // remove from tail
        node.pre.next = node.next;
        node.next.pre = node.pre;

        // bind to top
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;

        return node.value;
    }

    public void put(int key, int value) {
        Node oldNode = cache.get(key);
        if (null != oldNode){
            oldNode.value = value;
            if (oldNode.pre == head){
                return;
            }
            oldNode.next.pre = oldNode.pre;
            oldNode.pre.next = oldNode.next;

            head.next.pre = oldNode;
            oldNode.next = head.next;
            oldNode.pre = head;
            head.next = oldNode;
            return;

        }
        Node node = new Node(key,value);
        cache.put(key,node);
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;

        if (size < cache.size()){
            removeNode(tail.pre);
        }
    }

    private void removeNode(Node remove) {
        remove.next.pre = remove.pre;
        remove.pre.next = remove.next;
        cache.remove(remove.key);
    }

    private static class Node {

        private final int key;
        private int value;
        private Node next;
        private Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1) == 1);      // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2) == -1);      // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1) == -1);       // 返回 -1 (未找到)
        System.out.println(cache.get(3) == 3);       // 返回  3
        System.out.println(cache.get(4) == 4);       // 返回  4

        LRUCache cache2 = new LRUCache( 2 /* 缓存容量 */ );
        cache2.put(2,1);
        cache2.put(1,1);
        cache2.put(2,3);
        cache2.put(4,1);
        System.out.println(cache2.get(1) == -1);
        System.out.println(cache2.get(2) == 3);
        System.out.println(cache2.get(4) == 1);

    }
}

