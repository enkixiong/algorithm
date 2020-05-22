package com.sfmd.algorithm.leetCode.stack.solution20;

public class Solution {

    public boolean isValid(String s) {

        char[] charArray = s.toCharArray();
        ArrayStack arrayStack = new ArrayStack(charArray.length);

        for (int i = 0; i < charArray.length; i++){
            char current = charArray[i];
            byte charValue = 10;
            if (current == '}' || current == ')' || current == ']'){
                if (arrayStack.size == 0){
                    return false;
                }
                charValue = arrayStack.pop();
            }
            switch(current){
                case '{':
                    arrayStack.push((byte) 0);
                    break;
                case '(':
                    arrayStack.push((byte)1);
                    break;
                case '[':
                    arrayStack.push((byte)2);
                    break;
                case '}':
                    if (charValue != (byte)0){
                        return false;
                    }
                    break;
                case ')':
                    if (charValue != (byte)1){
                        return false;
                    }
                    break;
                case ']':
                    if (charValue != (byte)2){
                        return false;
                    }
                    break;
            }
        }
        return arrayStack.size == 0;
    }

    public static class ArrayStack {

        byte[] charStack;

        int size;

        public ArrayStack(int size) {
            this.size = 0;
            charStack = new byte[size];
        }

        public void push( byte value){
            // 边界值检测
            charStack[size] = value;
            size ++;
        }

        public byte pop(){
            size--;
            return charStack[size];
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid(""));
        System.out.println(new Solution().isValid("A"));
        System.out.println(new Solution().isValid("AB"));
        System.out.println(!new Solution().isValid("{"));
        System.out.println(!new Solution().isValid("["));
        System.out.println(!new Solution().isValid("("));
        System.out.println(!new Solution().isValid("{["));
        System.out.println(new Solution().isValid("{}"));
        System.out.println(new Solution().isValid("[]"));
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("(A)"));
        System.out.println(new Solution().isValid("(AB)"));
        System.out.println(!new Solution().isValid("({)"));
        System.out.println(new Solution().isValid("{{{{}}}}"));
    }

}
