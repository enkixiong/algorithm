package com.sfmd.algorithm.leetCode.s79;

class Solution {

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        int[][] dp = new int[board.length][board[0].length];
        char[] pattern = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == pattern[0]) {
                    dp[i][j] = 1;
                    if (match(board, dp, pattern, i, j)) {
                        return true;
                    }
                    dp[i][j] = 0;
                }
            }
        }

        return false;
    }

    public boolean match(char[][] main, int[][] dp, char[] pattern, int i, int j) {
        int nextTurn = dp[i][j];
        if (nextTurn == pattern.length) {
            return true;
        }
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            boolean canJump = nextI >= 0 && nextI < main.length
                    && nextJ >= 0 && nextJ < main[0].length
                    && main[nextI][nextJ] == pattern[nextTurn]
                    && dp[nextI][nextJ] == 0;
            if (canJump) {
                dp[nextI][nextJ] = nextTurn+1;
                if (match(main, dp, pattern, nextI, nextJ)) {
                    return true;
                }
                dp[nextI][nextJ] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        System.out.println(new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        System.out.println(new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB"));
    }


}
