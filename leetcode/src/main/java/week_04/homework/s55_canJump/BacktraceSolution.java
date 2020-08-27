package week_04.homework.s55_canJump;

class BacktraceSolution {
    public boolean canJump(int[] nums) {
        if (nums[0] == 0){
            return nums.length == 1;
        }

        return backtrace(nums, nums.length-1);
    }

    private boolean backtrace(int[] nums, int end){
        for(int i = end-1; i >= 0; i--){

            if (nums[i] != 0){
                continue;
            }

            for(int j = i-1; j >= 0; j--){

                if (nums[j] + j  > i && backtrace(nums,j)){
                    return true;
                }
            }

            return false;

        }
        return true;
    }

}
