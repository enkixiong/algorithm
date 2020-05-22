package com.sfmd.algorithm.leetCode.stack.solution155;

public class MinStack {

    private Node data;

    public MinStack() {
        data = new Node(Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

    public void push(int x) {
        Node node = new Node(x, Math.min(x, Math.min(data.min, x)));
        node.next = data.next;
        data.next = node;
        data.min = data.next.min;
    }

    public void pop() {
        data.next = data.next.next;
        data.min = data.next == null ? Integer.MAX_VALUE : data.next.min;
    }

    public int top() {
        return data.next.value;
    }

    public int getMin() {
        return data.min;
    }


    private static class Node {
        private int value;
        private int min;
        private Node next;

        public Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin() == -3);
        minStack.push(2);
        System.out.println(minStack.getMin() == -3);
        minStack.pop();
        System.out.println(minStack.getMin() == -3);
        minStack.pop();
        System.out.println(minStack.top() == 0);
        System.out.println(minStack.getMin() == -2);
        minStack.pop();
        System.out.println(minStack.getMin() == -2);
        minStack.pop();
    }

}
