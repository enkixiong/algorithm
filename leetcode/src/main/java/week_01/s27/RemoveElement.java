package week_01.s27;

class RemoveElement {

    // 注意: 这里并未使用数组中的元素作为是否更新的判断依据,所以, 不需要定义边界条件
    public int removeElement(int[] nums, int val) {
        // 更新指针
        int j = -1;

        for (int i = 0; i < nums.length; i++){
            // 是否允许更新
            if ( nums[i] != val ) {
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }

}
