package week_04.homework.s874_robotSim;

class Solution {
    private int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    private int direcIndex = 0;
    private int[] prePosition = new int[]{0,-1};
    private int[] position = new int[]{0,0};
    public int robotSim(int[] commands, int[][] obstacles) {
        for(int command : commands){
            if(command > 0){
                int[] direction = directions[direcIndex];
                for(int step : commands){
                    int[] nextPostion = new int[]{position[0]+direction[0], position[1]+direction[1]};
                    if(!canMove(position, nextPostion, obstacles)){
                        break;
                    }
                    position = nextPostion;
                }
                continue;
            }
            direcIndex = (direcIndex + (command == -1 ? 1 : 3)) % 4;
        }

        return position[0]*position[0] + position[1]*position[1];
    }

    private boolean canMove(int[] position, int[] nextPosition, int[][] obstacles){
        int nextX = nextPosition[0];
        if(nextX <= 0 || nextX > obstacles.length){
            return true;
        }
        int[] obstacle = obstacles[nextX-1];
        return true;
    }

}
