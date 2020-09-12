package week_06.s363;

class Solution {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 矩形, 有四个变量: 起点&终点, 终点只能在起点的右下方
        // 首先计算信息, 将0,0 -> 任意坐标的面积计算出来,记为info
        // result = info[x2][y2] + info[x1][y1] - info[x1][y2] - info[x2][y1];

        int row = matrix.length;
        int column = matrix[0].length;

        int[][] info = new int[row + 1][column + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                info[i][j] = info[i][j - 1] + info[i - 1][j] - info[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int max = Integer.MIN_VALUE;
        // 枚举矩形
        for (int x1 = 0; x1 < row; x1++) {
            for (int y1 = 0; y1 < column; y1++) {
                for (int x2 = x1 + 1; x2 <= row; x2++) {
                    for (int y2 = y1 + 1; y2 <= column; y2++) {
                        int area = info[x2][y2] + info[x1][y1] - info[x1][y2] - info[x2][y1];
                        if (area == k) {
                            return k;
                        }
                        if (area > k) {
                            continue;
                        }
                        max = Math.max(max, area);
                    }
                }
            }
        }

        return max;
    }

}
