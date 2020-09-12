package week_06.s410;

class DFSSolution {

    int[] info;

    int min = Integer.MAX_VALUE;

    // 深度优选遍历,超时
    public int splitArray(int[] nums, int m) {

        info = new int[nums.length + 1];
        info[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            info[i + 1] = info[i] + nums[i];
        }

        int[] sum = new int[m];
        dfs(0, 0, sum);

        return min;
    }

    private void dfs(int endIndex, int cellIndex, int[] sum) {

        if (cellIndex == sum.length - 1) {
            int cell = info[info.length - 1] - info[endIndex];
            sum[cellIndex] = cell;
            for (int value : sum) {
                cell = Math.max(cell, value);
            }
            min = Math.min(min, cell);
            return;
        }

        // cellIndex == 0 时, 允许到的最远的地方是: 3 = 5 - 1 - (2 - 1)
        int reminder = info.length - (sum.length - cellIndex);
        for (int i = endIndex + 1; i <= reminder; i++) {
            int cell = info[i] - info[endIndex];
            // 提前剪枝
            if (cell > min) {
                return;
            }
            sum[cellIndex] = cell;
            dfs(i, cellIndex + 1, sum);
        }
    }


}
