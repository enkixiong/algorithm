package week_06.s312;

class DPSolution {

    public int maxCoins(int[] nums) {

        // 1. 回溯法, 时间复杂度为 n!，时间复杂度太高
        // 2. DP: 对于DP来说，最难的问题在于, 数组是动态变化的; 而数组的拷贝和信息的存储复用就成了难点;
        // 难点的关键在于: 无法拆解子问题,并且是可记录的子问题;
        // 问题的根本在于: 戳破气球之后,该气球消失，并且与该气球相邻的气球会变得相邻; 在这样的问题下，可以考虑的点在于:
        //    如果我们记录的状态是最后一个戳破的气球呢？这样的话,在子问题求解时,将会出现一种现象:
        //    [i,k,j] --> donate[k] + donate[i,k] + donate[k,j]
        // 因为K是最后一个戳破的气球,所以, [i,k] 与 [k,j] 没有影响,可以递归求解;
        // 现在的问题在于: 如何定义donate[k]; 对于最后一个戳破的气球: 肯定是: val(k)*1*1
        // 在闭区间中无法解决这个问题，根本原因是: [i,k,j]中,如果K是[i,j]最后一个爆炸的气球,那么与K相乘的元素,是val(i-1)和val(j+1)
        // 因为k爆炸时,需要使用开区间元素,那么这里定义状态的根本为: (i,j) = val(i)*val(j)*val(k) + (i,k) + (k,j)

        // 拓展
        int[] newNums = new int[nums.length+2];
        newNums[0] = 1;
        newNums[newNums.length-1] = 1;
        System.arraycopy(nums,0,newNums,1, nums.length);
        nums = newNums;

        int len = nums.length;
        int[][] dp = new int[len][len];
        // dp[i][j] 表示的是开区间 (i,j)的结果

        // 递推的方式: j-i 由2 --> len-1 duration:开区间的长度(j-i的值)
        for(int duration = 2; duration < len; duration++){
            int end = len-duration;
            for(int i = 0; i < end; i++){
                int j = i + duration;
                // 求解开区间的值 k是区间内最后一个戳的气球,所以必须真实存在;开区间中(i,j)真实存在的元素时[i+1,j-1]
                for(int k = i+1; k < j; k++){
                    int val = dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j];
                    dp[i][j] = Math.max(val,dp[i][j]);
                }
            }
        }
        return dp[0][len-1];
    }

}
