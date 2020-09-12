package week_06.s32;

import java.util.ArrayDeque;
import java.util.Deque;

class StackSolution {

    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>(s.length()+1);
        char[] main = s.toCharArray();
        stack.push(-1);

        int max = 0;
        for(int i = 0; i < main.length; i++){
            if(main[i] == '('){
                stack.push(i);
            }else{
                int top = stack.peek();
                if(top >= 0 && main[top] == '('){
                    stack.pop();
                    max = Math.max(max, i - stack.peek());
                }else{
                    stack.push(i);
                }
            }
        }
        return max;
    }

}
