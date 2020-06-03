package com.sfmd.algorithm.leetCode.slideWindow.solution84;


import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class LargestRectangleArea {


    // 关键参数: 1. [left,right]中minIndex的值以及索引 2. [left,right]区间长度
    // 2. 迭代计算 [left,minIndex-1],[minIndex+1,right] 的值, 取最大值；
    // 退出条件:
    // left > right
    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length - 1);
    }

    public int largestRectangleArea(int[] heights, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return heights[left];
        }
        int minIndex = left;
        for (int i = left; i <= right; i++) {
            if (heights[minIndex] > heights[i]) {
                minIndex = i;
            }
        }
        int currentRectangleArea = (right - left + 1) * heights[minIndex];
        int leftRectangleArea = largestRectangleArea(heights, left, minIndex - 1);
        int rightRectangleArea = largestRectangleArea(heights, minIndex + 1, right);
        return Math.max(Math.max(currentRectangleArea, leftRectangleArea), rightRectangleArea);
    }

    // 单调栈
    public int largestRectangleArea2(int[] heights) {
        int[] left = new int[heights.length];
        Stack stack = new Stack(-1, -1);
        for (int i = 0; i < heights.length; i++) {
            while (heights[i] <= stack.peek().value) {
                stack.pop();
            }
            left[i] = stack.peek().offset;
            stack.push(i, heights[i]);
        }
        System.out.println(JSON.toJSONString(left));
        int[] right = new int[heights.length];
        stack = new Stack(heights.length, -1);
        for (int i = heights.length - 1; i >= 0; i--) {
            while (heights[i] <= stack.peek().value) {
                stack.pop();
            }
            right[i] = stack.peek().offset;
            stack.push(i, heights[i]);
        }
        System.out.println(JSON.toJSON(right));
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max((right[i] - left[i] - 1) * heights[i], max);
        }
        System.out.println(max);
        return max;
    }


    private static class Stack {

        private Node head;
        private Node tail;

        public Stack(int offset, int value) {
            head = new Node(offset, value);
            tail = new Node(offset, value);
            head.next = tail;
        }


        public void push(int offset, int value) {
            Node node = new Node(offset, value);
            node.next = head.next;
            head.next = node;
        }

        public Node peek() {
            return head.next;
        }

        public Node pop() {
            Node node = head.next;
            head.next = head.next.next;
            return node;
        }

        public void clear() {
            head.next = tail;
        }

    }

    private static class Node {
        private int offset;
        private int value;
        private Node next;

        public Node(int offset, int value) {
            this.offset = offset;
            this.value = value;
        }
    }

    public int largestRectangleArea3(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Arrays.fill(right, heights.length);
        Stack2 stack = new Stack2(-1);
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek().offset]) {
                Node2 node = stack.pop();
                right[node.offset] = i;
            }
            left[i] = stack.peek().offset;
            stack.push(i);
        }
        System.out.println(JSON.toJSONString(left));
        System.out.println(JSON.toJSONString(right));
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max((right[i] - left[i] - 1) * heights[i], max);
        }
        return max;
    }

    private static class Stack2 {

        private Node2 head;
        private Node2 tail;

        public Stack2(int offset) {
            head = new Node2(offset);
            tail = new Node2(offset);
            head.next = tail;
        }


        public void push(int offset) {
            Node2 node = new Node2(offset);
            node.next = head.next;
            head.next = node;
        }

        public Node2 peek() {
            return head.next;
        }

        public Node2 pop() {
            Node2 node = head.next;
            head.next = head.next.next;
            return node;
        }

        public boolean isEmpty(){
            return head.next == tail;
        }

    }

    private static class Node2 {
        private final int offset;
        private Node2 next;

        public Node2(int offset) {
            this.offset = offset;
        }
    }

    public int largestRectangleArea4(int[] heights){
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        java.util.Stack<Integer> mono_stack = new java.util.Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{2,1,5,6,2,3}) == 10);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{0,9}) == 9);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{1}) == 1);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{1,1}) == 2);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{1,1,1}) == 3);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{6,7,5,2,4,5,9,3}) == 16);
        System.out.println(new LargestRectangleArea().largestRectangleArea2(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6}) == 12);
        System.out.println(new LargestRectangleArea().largestRectangleArea4(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6}) == 12);

    }

}
