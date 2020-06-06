package com.sfmd.algorithm.leetCode.s29;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        String direction = "right";
        int offset = 0;
        int[] result = new int[matrix[0].length * matrix.length];
        while (offset < result.length) {
            switch (direction) {
                case "right":
                    for (int i = left; i <= right; i++, offset++) {
                        result[offset] = matrix[top][i];
                    }
                    top++;
                    direction = "down";
                    break;
                case "down":
                    for (int i = top; i <= bottom; i++, offset++) {
                        result[offset] = matrix[i][right];
                    }
                    right--;
                    direction = "left";
                    break;
                case "left":
                    for (int i = right; i >= left; i--, offset++) {
                        result[offset] = matrix[bottom][i];
                    }
                    bottom--;
                    direction = "up";
                    break;
                case "up":
                    for (int i = bottom; i >= top; i--, offset++) {
                        result[offset] = matrix[i][left];
                    }
                    left++;
                    direction = "right";
            }
        }
        return result;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return Collections.emptyList();
        }

        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;
        String direction = "right";
        int size = matrix[0].length * matrix.length;
        List<Integer> result = new ArrayList<>(size);

        while (result.size() < size) {
            switch (direction) {
                case "right":
                    for (int i = left; i <= right; i++) {
                        result.add(matrix[top][i]);
                    }
                    top++;
                    direction = "down";
                    break;
                case "down":
                    for (int i = top; i <= bottom; i++) {
                        result.add(matrix[i][right]);
                    }
                    right--;
                    direction = "left";
                    break;
                case "left":
                    for (int i = right; i >= left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    bottom--;
                    direction = "up";
                    break;
                case "up":
                    for (int i = bottom; i >= top; i--) {
                        result.add(matrix[i][left]);
                    }
                    left++;
                    direction = "right";
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        System.out.println(new Solution().spiralOrder(new int[][]{{}}).length == 0 );
//        System.out.println(new Solution().spiralOrder(new int[][]{{1}})[0] == 1);
//        System.out.println(JSON.toJSONString(new Solution().spiralOrder(new int[][]{{1,2}})));
//        System.out.println(JSON.toJSONString(new Solution().spiralOrder(new int[][]{{1,2},{3,4}})));
//        System.out.println(JSON.toJSONString(new Solution().spiralOrder(new int[][]{{1},{3}})));
        System.out.println(JSON.toJSONString(new SpiralOrder().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
        System.out.println(JSON.toJSONString(new SpiralOrder().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})));
    }

}
