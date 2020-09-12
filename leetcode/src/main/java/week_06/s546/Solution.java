package week_06.s546;

class Solution {

    int[] boxes;

    int[][][] memo;

    public int removeBoxes(int[] boxes) {
        // [1,3,2,2,2,3,4,3,1] --> [1,3,2,2,2,3,,3,1] --> [1,3,,,,3,,3,1] --> [1,[,]*7,1] --> [,]*9
        // 1. 首先,移出相同的元素,获取积分, 解题思路中,就需要将相同的元素尽量合并
        // 2. 与元素具体的值无关,只跟连续性有关

        //根据参考答案, 来定义状态
        // f(l,r,k) -> 表示的意思是,区间[l,r] + [r]*k
        // 样例: f(0,8,0) --> f(0,0,1) + f(1,7,0) --> 2*2 + f(1,5,1) + f(6,6,0) --> 5+f(1,5,1)
        // --> f(1,1,2) + f(2,4,0)+ 5 --> 3*3 + 3*3 + 5 = 23;

        // 对于 f(l,r,k) 来说, 有几种决策:
        // f(l,r,k) == f(l,r-1,0) + (k+1)*(k+1)
        // f(l,r,k) == f(l,r(-1),k+1) + f(r(-1)+1, r-1,0) // r(-1)表示上一个元素与boxes[r]相同的元素
        this.boxes = boxes;
        memo = new int[boxes.length][boxes.length][boxes.length];
        return dfs(0, boxes.length - 1, 0);
    }

    private int dfs(int left, int right, int repeat) {
        // terminator
        if (right < left) {
            return 0;
        }
        // 记忆化
        if (memo[left][right][repeat] != 0) {
            return memo[left][right][repeat];
        }

        while(right > left && boxes[right] == boxes[right-1]){
            right--;
            repeat++;
        }

        int ans = dfs(left,right-1,0) + (repeat+1)*(repeat+1);
        // 注意这里有坑, 我们只考虑 repeat+1的情况, 不考虑 repeat+2 ... 等以后的情况, repeat+2的情况, 交给repeat+1的情况处理
        // 本层,只考虑当前层以及下一层的情况

        // TODO 请注意,在进行DFS时,需要明确的定义当前层的状态和drill down的状态
        for(int i = left; i < right; i++){
            if(boxes[i] == boxes[right]){
                ans = Math.max(ans, dfs(left, i, repeat+1) + dfs(i+1, right-1, 0));
            }
        }
        memo[left][right][repeat] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
        System.out.println(new Solution().removeBoxes(new int[]{1}));
        System.out.println(new Solution().removeBoxes(new int[]{1, 1}));
        System.out.println(new Solution().removeBoxes(new int[]{1, 1, 1}));
        System.out.println(new Solution().removeBoxes(new int[]{3,8,8,5,5,3,9,2,4,4,6,5,8,4,8,6,9,6,2,8,6,4,1,9,5,3,10,5,3,3,9,8,8,6,5,3,7,4,9,6,3,9,4,3,5,10,7,6,10,7}));
    }

}
