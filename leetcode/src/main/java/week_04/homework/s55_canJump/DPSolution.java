package week_04.homework.s55_canJump;

public class DPSolution {

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length-1]=true;
        for(int i = nums.length-2; i >= 0; i--){
            for(int j = 1; j <= nums[i]; j++){
                if(i+j < nums.length && dp[i+j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

}
