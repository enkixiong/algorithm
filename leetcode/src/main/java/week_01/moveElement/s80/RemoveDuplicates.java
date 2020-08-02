package week_01.moveElement.s80;

class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        // 边界条件
        if(nums.length == 0) {
            return 0;
        }
        // 初始值定义
        int j = 0;
        boolean hasTwo = false;

        for(int i = 1; i < nums.length; i++){
            // 新元素,允许更新
            if (nums[j] != nums[i]){
                nums[++j] = nums[i];
                hasTwo = false;
            }else if(!hasTwo){ // 已经出现,但是只出现一次的元素,允许更新;
                nums[++j] = nums[i];
                hasTwo = true;
            }
        }

        // 返回值
        return j+1;
    }

}
