package com.sfmd.algorithm.leetCode.s150;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {

    private static final List<String> operators = Arrays.asList("+","-","*","/");

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (operators.contains(token)){
                Integer right = Integer.parseInt(stack.pop());
                Integer left = Integer.parseInt(stack.pop());
                int value = 0;
                switch (token){
                    case "+":
                        value = left+right;
                        break;
                    case "-":
                        value = left - right;
                        break;
                    case "*":
                        value = left * right;
                        break;
                    case "/":
                        value = left / right;
                        break;
                }
                stack.push(String.valueOf(value));
            }else{
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[]{"2","1","+","3","*"}));
    }

}
