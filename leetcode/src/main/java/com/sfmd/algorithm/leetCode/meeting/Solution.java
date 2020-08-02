package com.sfmd.algorithm.leetCode.meeting;

import com.alibaba.fastjson.JSON;

class Solution {

    private int result;

    int[][] directions = new int[][]{{-1,0},{0,-1}};

    private int findResult(char[][] input){
        int[][] choose = new int[6][6];
        findResult(input, choose, 0, 0);
        return result;
    }

    private void findResult(char[][] input, int[][] choose, int i, int j){
        if (j == 6){
            i++;
            j = 0;
        }

        if (i == 6){
            result += 1;
            return;
        }

        if (input[i][j] == '*'){
            findResult(input,choose, i, j+1);
            return;
        }

        int remove = 0;
        for (int[] direction : directions) {
            int preI = direction[0] + i;
            int preJ = direction[1] + j;
            if (preI >= 0 && preJ >= 0) {
                remove |= (0x01 << (choose[preI][preJ] - 1));
            }
        }
        int seed = 0b111111 - remove;
        for(int count = 1; count <= 6; count++){
            // 检测最低位是不是为1
            if ((seed & 0b01) == 1){
                // 是的话,就表明可以选该值
                choose[i][j] = count;
                // 进行下一个取值
                findResult(input,choose, i, j+1);
            }
            // 种子生成器右移
            seed = (seed >>1);
        }
    }



    public static void main(String[] args) {
        char[][] input = new char[][]{{'#','*','*','*','*','*'},{'#','*','*','*','*','*'},{'#','*','*','*','*','*'},{'*','*','*','*','*','*'},{'*','*','*','*','*','*'},{'*','*','*','*','*','*'}};
        System.out.println(new Solution().findResult(input));
    }

}
