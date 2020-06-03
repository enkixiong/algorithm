package com.sfmd.algorithm.leetCode.stack.s224;

public class Calculator {

    public int calculate(String s) {

        Stack stack = new Stack();
        char[] charArray = s.toCharArray();
        int valueStart = -1;

        for (int i = 0; i < charArray.length; i++){
            char charValue = charArray[i];
            if (charValue == ' '){
                continue;
            }
            if (charValue >= 0x30  && charValue <= 0x39){
                if (valueStart == -1){
                    valueStart = i;
                }
                continue;
            }
            if (valueStart != -1){
                int value = Integer.parseInt(s.substring(valueStart,i));
                stack.pushValue(value);
                valueStart = -1;
            }

            switch(charValue){
                case '+':
                case '-':
                case '(':
                    stack.push(charValue);
                    break;
                case ')':
                    Number number = (Number) stack.pop();
                    stack.pop();
                    stack.pushNode(number);
                    break;
            }
        }

        if (valueStart != -1){
            int value = Integer.parseInt(s.substring(valueStart));
            stack.pushValue(value);
        }

        System.out.println(stack);
        return 0;
    }

    private static class Stack {

        private final Node head = new Operator('a');

        private void push(char value){
            Operator operator = new Operator(value);
            bind(operator);
        }

        private void pushValue(int value){
            Number number = new Number(value);
            bind(number);
        }

        private void pushNode(Node node){
            bind(node);
        }

        private void bind(Node node){
            node.next = head.next;
            head.next = node;
        }

        private Node pop(){
            Node node = head.next;
            head.next = head.next.next;
            return node;
        }

        private Node peek(){
            return head.next;
        }

        @Override
        public String toString() {
            return "Stack{" + head + '}';
        }
    }

    private static class Node {
        Node next;
    }

    private static class Operator extends Node{
        char operator;

        public Operator(char operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return " "+operator+" " + next;
        }
    }

    private static class Number extends Node{
        int value;

        public Number(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return " "+value+" " + next;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Calculator().calculate("12+123") == 135);
        System.out.println(new Calculator().calculate("1 + 1") == 2);
        System.out.println(new Calculator().calculate("2 - 1 + 2") == 3);
        System.out.println(new Calculator().calculate("(1+(4+5+2)-3)+(6+8)") == 23);
    }

}
