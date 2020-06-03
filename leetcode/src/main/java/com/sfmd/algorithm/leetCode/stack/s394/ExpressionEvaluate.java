package com.sfmd.algorithm.leetCode.stack.s394;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

public class ExpressionEvaluate {

    public String decodeString(String s) {
        char[] charArray = s.toCharArray();
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < charArray.length; i++){
            char charValue = charArray[i];
            if (charValue == '['){
                stack.push(charValue);
                continue;
            }
            if (charValue == ']'){
                evaluate(stack);
                continue;
            }
            int next = findNextSeparator(charArray, i);
            stack.push(s.substring(i,next));
            i = next -1;
        }
        String tail = "";
        while(!stack.isEmpty()){
            String head = (String) stack.pop();
            tail = head.concat(tail);
        }
        return tail;
    }

    private void evaluate(Stack<Object> stack) {
        String tmp = "";
        while(!stack.isEmpty()){
            Object o = stack.pop();
            if (o instanceof String){
                tmp = ((String)o).concat(tmp);
                continue;
            }
            if (o instanceof Character && ((Character)o) == '['){
                break;
            }
        }
        int num = Integer.parseInt((String) stack.pop());
        String computed = tmp;
        for (int j = 1; j < num; j++){
            computed = computed.concat(tmp);
        }
        stack.push(computed);
    }

    /**
     * 可能会产生数组越界
     */
    public int findNextSeparator(char[] charArray, int startIndex){
        char charValue = charArray[startIndex];
        boolean flag = Character.isDigit(charValue);
        for (int i = startIndex+1; i < charArray.length; i++) {
            char currentCharValue = charArray[i];
            if ((flag != Character.isDigit(currentCharValue)) || currentCharValue == '[' || currentCharValue == ']') {
                return i;
            }
            if (i + 1 == charArray.length){
                return i + 1;
            }
        }
        return startIndex+1;
    }


    public static void main(String[] args) {
        System.out.println(new ExpressionEvaluate().decodeString("3[a]2[bc]").equals("aaabcbc"));
        System.out.println(new ExpressionEvaluate().decodeString("3[a2[c]]").equals("accaccacc"));
//        System.out.println(new ExpressionEvaluate().decodeString("32[a22[cd]]"));
        System.out.println(new ExpressionEvaluate().decodeString("2[abc]3[cd]ef").equals("abcabccdcdcdef"));
    }



}
