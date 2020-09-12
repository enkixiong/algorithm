package week_06.s152;

class DPSolution {

    public int maxProduct(int[] nums) {
        //  对于连续子数组的问题, 都会有一个选择: 是以当前元素为起始点还是黏合当前元素?
        // 乘法与加法不同的点在于, 乘法会产生变号的问题, 所以需要维护两个值, 最大值&最小值

        int min = 1;
        int max = 1;

        int result = nums[0];

        for(int num : nums){
            int nextMax = Math.max(num > 0 ? max * num : min * num, num);
            min = Math.min(num < 0 ? max * num : min * num, num);
            max = nextMax;
            result = Math.max(max, result);
        }

        return result;

    }

}
