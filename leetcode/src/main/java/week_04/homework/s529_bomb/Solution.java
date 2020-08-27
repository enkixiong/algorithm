package week_04.homework.s529_bomb;

class Solution {

    private static final int[][] DIRECTIONS = new int[][]{{-1,-1},{-1,1},{1,1},{1,-1},{-1,0},{1,0},{0,-1},{0,1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        // 挖到雷
        if (board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }

        // 从该点开始挖
        dfs(board,x,y);

        return board;
    }

    // 该位置不可能为雷
    private void dfs(char[][] board, int x, int y){
        // terminator: 出界/已挖
        if (x < 0 || x == board.length || y < 0 || y == board[0].length || board[x][y] != 'E'){
            return;
        }

        // 2: 检测相邻位置是否有雷,决定是否往下挖
        int bomb = 0;
        for(int[] direction : DIRECTIONS){
            if (isBomb(board,x+direction[0],y+direction[1])){
                bomb++;
            }
        }
        if (bomb != 0){
            board[x][y] = (char)(bomb + '0');
            return;
        }

        // 3: 如果发现未发现雷,向相邻位置挖
        board[x][y] = 'B';
        for(int[] direction : DIRECTIONS){
            dfs(board, x+direction[0], y+direction[1]);
        }

    }

    private boolean isBomb(char[][] board, int x,int y){
        if (x < 0 || x == board.length || y < 0 || y == board[0].length){
            return false;
        }
        return board[x][y] == 'M';
    }

}
